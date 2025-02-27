/*
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.validation.table;

import com.gs.dmn.DMNModelRepository;
import com.gs.dmn.QualifiedName;
import com.gs.dmn.ast.*;
import com.gs.dmn.context.DMNContext;
import com.gs.dmn.el.analysis.semantics.type.Type;
import com.gs.dmn.el.analysis.syntax.ast.expression.Expression;
import com.gs.dmn.el.synthesis.ELTranslator;
import com.gs.dmn.feel.analysis.syntax.ast.expression.literal.BooleanLiteral;
import com.gs.dmn.feel.analysis.syntax.ast.expression.literal.NumericLiteral;
import com.gs.dmn.feel.analysis.syntax.ast.expression.literal.SimpleLiteral;
import com.gs.dmn.feel.analysis.syntax.ast.expression.literal.StringLiteral;
import com.gs.dmn.feel.analysis.syntax.ast.test.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.gs.dmn.validation.table.Bound.MINUS_INFINITY;
import static com.gs.dmn.validation.table.Bound.PLUS_INFINITY;

public class TableFactory {
    public Table makeTable(int totalNumberOfRules, int totalNumberOfColumns, DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable, ELTranslator<Type, DMNContext> feelTranslator) {
        List<Input> inputs = makeInputs(repository, element, decisionTable);
        List<Rule> rules = makeRules(totalNumberOfRules, totalNumberOfColumns, inputs, repository, element, decisionTable, feelTranslator);
        return new Table(inputs, rules);
    }

    public Interval makeIntervalMin(int columnIndex, Bound lastBound, Bound currentBound, Table table) {
        Input input = table.getInput(columnIndex);
        Double startValue = makeBoundValue(true, lastBound);
        Double endValue = makeBoundValue(false, currentBound);
        if (input.isNumberType()) {
            return new NumericInterval(-1, columnIndex, input, lastBound.isIncluded(), startValue, !currentBound.isIncluded(), endValue);
        } else if (input.isBooleanType() || input.isStringType()) {
            return new EnumerationInterval(-1, columnIndex, input, lastBound.isIncluded(), startValue, !currentBound.isIncluded(), endValue);
        }
        throw new IllegalArgumentException(String.format("Unexpected interval type for bounds '%s' or '%s'", lastBound, currentBound));
    }

    public Interval makeIntervalMax(int columnIndex, Bound lastBound, Bound currentBound, Table table) {
        Input input = table.getInput(columnIndex);
        Double startValue = makeBoundValue(true, lastBound);
        Double endValue = makeBoundValue(false, currentBound);
        if (input.isNumberType()) {
            return new NumericInterval(-1, columnIndex, input, !lastBound.isIncluded(), startValue, currentBound.isIncluded(), endValue);
        } else if (input.isBooleanType() || input.isStringType()) {
            return new EnumerationInterval(-1, columnIndex, input, !lastBound.isIncluded(), startValue, currentBound.isIncluded(), endValue);
        }
        throw new IllegalArgumentException(String.format("Unexpected interval type for bounds '%s' or '%s'", lastBound, currentBound));
    }

    public Interval makeInterval(int columnIndex, Bound lastBound, Bound currentBound, Table table) {
        Input input = table.getInput(columnIndex);
        Double startValue = makeBoundValue(true, lastBound);
        Double endValue = makeBoundValue(false, currentBound);
        if (input.isNumberType()) {
            return new NumericInterval(-1, columnIndex, input, !lastBound.isIncluded(), startValue, !currentBound.isIncluded(), endValue);
        } else if (input.isBooleanType() || input.isStringType()) {
            return new EnumerationInterval(-1, columnIndex, input, !lastBound.isIncluded(), startValue, !currentBound.isIncluded(), endValue);
        }
        throw new IllegalArgumentException(String.format("Unexpected interval type for bounds '%s' or '%s'", lastBound, currentBound));
    }

    private Double makeBoundValue(boolean isLower, Bound bound) {
        if (bound == null || bound.getValue() == null) {
            return  isLower ? MINUS_INFINITY : PLUS_INFINITY;
        } else {
            return (Double) bound.getValue();
        }
    }

    private List<Input> makeInputs(DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable) {
        List<Input> inputs = new ArrayList<>();
        int numberOfColumns = decisionTable.getInput().size();
        for (int columnIndex=0; columnIndex<numberOfColumns; columnIndex++) {
            String inputTypeRef = getInputType(decisionTable, columnIndex);
            if (Input.isNumberType(inputTypeRef)) {
                inputs.add(new Input(inputTypeRef));
            } else if (Input.isBooleanType(inputTypeRef)) {
                inputs.add(new Input(inputTypeRef, EnumerationInterval.BOOLEAN_ALLOWED_VALUES));
            } else {
                // Retrieve allowed values for enumerations
                TDefinitions model = repository.getModel(element);
                TItemDefinition itemDefinition = repository.lookupItemDefinition(model, QualifiedName.toQualifiedName(model, inputTypeRef));
                if (itemDefinition != null) {
                    String typeRef = QualifiedName.toName(itemDefinition.getTypeRef());
                    if ("string".equals(typeRef)) {
                        List<String> allowedValues = findAllowedValues(repository, element, decisionTable, columnIndex);
                        if (!allowedValues.isEmpty()) {
                            inputs.add(new Input(typeRef, allowedValues));
                        }
                    }
                }
            }
        }
        if (inputs.size() < numberOfColumns) {
            inputs.clear();
        }
        return inputs;
    }

    private List<Rule> makeRules(int totalNumberOfRules, int totalNumberOfColumns, List<Input> inputs, DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable, ELTranslator<Type, DMNContext> feelTranslator) {
        List<Rule> rules = new ArrayList<>();
        if (inputs.isEmpty()) {
            return rules;
        }

        for (int ruleIndex=0; ruleIndex<totalNumberOfRules; ruleIndex++) {
            TDecisionRule rule = decisionTable.getRule().get(ruleIndex);
            List<Interval> intervals = new ArrayList<>();
            for (int columnIndex=0; columnIndex<totalNumberOfColumns; columnIndex++) {
                Input input = inputs.get(columnIndex);
                List<TUnaryTests> inputEntry = rule.getInputEntry();
                TUnaryTests cell = inputEntry.get(columnIndex);
                Interval interval = makeInterval(repository, element, decisionTable, ruleIndex, columnIndex, cell, input, feelTranslator);
                if (interval != null) {
                    intervals.add(interval);
                }
            }
            if (intervals.size() == totalNumberOfColumns) {
                rules.add(new Rule(intervals));
            } else {
                return new ArrayList<>();
            }
        }
        return rules;
    }

    private Interval makeInterval(DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable, int ruleIndex, int columnIndex, TUnaryTests cell, Input input, ELTranslator<Type, DMNContext> feelTranslator) {
        if (cell == null) {
            return null;
        }

        // Parse unary tests
        String text = cell.getText();
        UnaryTests<Type, DMNContext> unaryTests = (UnaryTests<Type, DMNContext>) feelTranslator.parseUnaryTests(text);
        if (unaryTests instanceof Any) {
            return makeAnyInterval(ruleIndex, columnIndex, input);
        } else if (unaryTests instanceof PositiveUnaryTests) {
            List<PositiveUnaryTest<Type, DMNContext>> positiveUnaryTests = ((PositiveUnaryTests<Type, DMNContext>) unaryTests).getPositiveUnaryTests();
            // Check simple expressions only
            if (positiveUnaryTests.size() == 1) {
                PositiveUnaryTest<Type, DMNContext> positiveUnaryTest = positiveUnaryTests.get(0);
                // Check intervals
                if (positiveUnaryTest instanceof EndpointsRange) {
                    return makeInterval(repository, element, decisionTable, ruleIndex, columnIndex, input, (EndpointsRange<Type, DMNContext>) positiveUnaryTest);
                } else if (positiveUnaryTest instanceof OperatorRange) {
                    return makeInterval(repository, element, decisionTable, ruleIndex, columnIndex, input, (OperatorRange<Type, DMNContext>) positiveUnaryTest);
                }
            }
        }
        return null;
    }

    private Interval makeAnyInterval(int ruleIndex, int columnIndex, Input input) {
        // Any unary test
        if (input.isNumberType()) {
            // Number - min, max interval is [-Infinity .. +Infinity]
            return new NumericInterval(ruleIndex, columnIndex, input);
        } else if (input.isBooleanType()) {
            // Boolean - min, max interval is [0..3)
            return new EnumerationInterval(ruleIndex, columnIndex, input);
        } else if (input.isStringType()) {
            List<String> allowedValues = input.getAllowedValues();
            if (!allowedValues.isEmpty()) {
                // Number - min, max interval is [0..max+1)
                return new EnumerationInterval(ruleIndex, columnIndex, input);
            }
        }
        return null;
    }

    private Interval makeInterval(DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable, int ruleIndex, int columnIndex, Input input, EndpointsRange<Type, DMNContext> astRange) {
        Expression<Type, DMNContext> start = astRange.getStart();
        Expression<Type, DMNContext> end = astRange.getEnd();
        if (start instanceof NumericLiteral && end instanceof NumericLiteral) {
            Double startValue = makeBoundValue(repository, element, decisionTable, columnIndex, start);
            Double endValue = makeBoundValue(repository, element, decisionTable, columnIndex, end);
            if (startValue != null && endValue != null) {
                boolean openEnd = astRange.isOpenEnd();
                boolean openStart = astRange.isOpenStart();
                if (openStart) {
                    startValue = startValue + Bound.DELTA;
                }
                if (openEnd) {
                    endValue = endValue - Bound.DELTA;
                }
                return new NumericInterval(ruleIndex, columnIndex, input, true, startValue, true, endValue);
            }
        }
        return null;
    }

    private Interval makeInterval(DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable, int ruleIndex, int columnIndex, Input input, OperatorRange<Type, DMNContext> operatorRange) {
        String operator = operatorRange.getOperator();
        Expression<Type, DMNContext> endpoint = operatorRange.getEndpoint();
        if (endpoint instanceof NumericLiteral) {
            // Number
            String lexeme = ((NumericLiteral<Type, DMNContext>) endpoint).getLexeme();
            Double value = Double.parseDouble(lexeme);
            if (operator == null) {
                return new NumericInterval(ruleIndex, columnIndex, input, true, value, true, value);
            } else if ("<".equals(operator)) {
                return new NumericInterval(ruleIndex, columnIndex, input, true, Bound.MINUS_INFINITY, false, value - Bound.DELTA);
            } else if ("<=".equals(operator)) {
                return new NumericInterval(ruleIndex, columnIndex, input, true, Bound.MINUS_INFINITY, true, value);
            } else if (">".equals(operator)) {
                return new NumericInterval(ruleIndex, columnIndex, input, false, value + Bound.DELTA, true, Bound.PLUS_INFINITY);
            } else if (">=".equals(operator)) {
                return new NumericInterval(ruleIndex, columnIndex, input, true, value, true, Bound.PLUS_INFINITY);
            }
        } else if (endpoint instanceof BooleanLiteral) {
            // Boolean
            if (operator == null) {
                String value = ((BooleanLiteral<Type, DMNContext>) endpoint).getLexeme();
                // create interval [i..i+1)
                return new EnumerationInterval(ruleIndex, columnIndex, input, value);
            }
        } else if (endpoint instanceof StringLiteral) {
            // Enumeration
            if (operator == null) {
                String value = ((SimpleLiteral<Type, DMNContext>) endpoint).getLexeme();
                List<String> allowedValues = findAllowedValues(repository, element, decisionTable, columnIndex);
                if (!allowedValues.isEmpty()) {
                    // create interval [i..i+1)
                    return new EnumerationInterval(ruleIndex, columnIndex, input, value);
                }
            }
       }
        return null;
    }

    private Double makeBoundValue(DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable, int columnIndex, Expression<Type, DMNContext> exp) {
        Double value = null;
        if (exp instanceof NumericLiteral) {
            String lexeme = ((SimpleLiteral<Type, DMNContext>) exp).getLexeme();
            value = Double.parseDouble(lexeme);
        } else if (exp instanceof BooleanLiteral) {
            String lexeme = ((SimpleLiteral<Type, DMNContext>) exp).getLexeme();
            boolean bValue = Boolean.parseBoolean(lexeme);
            value = bValue ? Bound.ONE : Bound.ZERO;
        } else if (exp instanceof StringLiteral) {
            String lexeme = ((SimpleLiteral<Type, DMNContext>) exp).getLexeme();
            List<String> allowedValues = findAllowedValues(repository, element, decisionTable, columnIndex);
            for (int i=0; i<allowedValues.size(); i++) {
                String enumValue = allowedValues.get(i);
                if (enumValue.equals(lexeme)) {
                    return (double) i;
                }
            }
        }
        return value;
    }

    private List<String> findAllowedValues(DMNModelRepository repository, TDRGElement element, TDecisionTable decisionTable, int columnIndex) {
        List<TInputClause> input = decisionTable.getInput();
        if (input != null && input.size() > columnIndex) {
            TInputClause inputClause = input.get(columnIndex);
            TLiteralExpression inputExpression = inputClause.getInputExpression();
            if (inputExpression != null) {
                String typeRef = QualifiedName.toName(inputExpression.getTypeRef());
                if (!StringUtils.isBlank(typeRef)) {
                    TDefinitions model = repository.getModel(element);
                    TItemDefinition tItemDefinition = repository.lookupItemDefinition(model, QualifiedName.toQualifiedName(model, typeRef));
                    if (tItemDefinition != null) {
                        TUnaryTests allowedValues = tItemDefinition.getAllowedValues();
                        if (allowedValues != null) {
                            String text = allowedValues.getText();
                            if (!StringUtils.isBlank(text)) {
                                String[] split = text.split(",");
                                List<String> enumValues = new ArrayList<>();
                                for (String part: split) {
                                    if (part != null) {
                                        enumValues.add(part.trim());
                                    }
                                }
                                return enumValues;
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private String getInputType(TDecisionTable decisionTable, int columnIndex) {
        List<TInputClause> input = decisionTable.getInput();
        String typeRef = "";
        if (input != null) {
            TInputClause inputClause = input.get(columnIndex);
            if (inputClause != null) {
                TLiteralExpression inputExpression = inputClause.getInputExpression();
                if (inputExpression != null) {
                    typeRef = QualifiedName.toName(inputExpression.getTypeRef());
                }
            }
        }
        return typeRef;
    }
}
