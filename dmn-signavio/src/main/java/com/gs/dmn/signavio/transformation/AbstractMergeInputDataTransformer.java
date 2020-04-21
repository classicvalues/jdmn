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
package com.gs.dmn.signavio.transformation;

import com.gs.dmn.DMNModelRepository;
import com.gs.dmn.log.BuildLogger;
import com.gs.dmn.log.Slf4jBuildLogger;
import com.gs.dmn.runtime.DMNRuntimeException;
import com.gs.dmn.runtime.Pair;
import com.gs.dmn.signavio.SignavioDMNModelRepository;
import com.gs.dmn.signavio.testlab.InputParameterDefinition;
import com.gs.dmn.signavio.testlab.TestCase;
import com.gs.dmn.signavio.testlab.TestLab;
import com.gs.dmn.signavio.testlab.expression.Expression;
import com.gs.dmn.transformation.SimpleDMNTransformer;
import org.omg.spec.dmn._20180521.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.*;

public abstract class AbstractMergeInputDataTransformer extends SimpleDMNTransformer<TestLab> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMergeInputDataTransformer.class);

    private final BuildLogger logger;
    private Map<String, Pair<TInputData, List<TInputData>>> inputDataClasses;
    private boolean forceMerge = true;

    public AbstractMergeInputDataTransformer() {
        this(new Slf4jBuildLogger(LOGGER));
    }

    public AbstractMergeInputDataTransformer(BuildLogger logger) {
        this.logger = logger;
    }

    @Override
    public DMNModelRepository transform(DMNModelRepository repository) {
        this.inputDataClasses = new LinkedHashMap<>();

        return mergeInputData(repository, logger);
    }

    @Override
    public Pair<DMNModelRepository, List<TestLab>> transform(DMNModelRepository repository, List<TestLab> testLabList) {
        if (inputDataClasses == null) {
            transform(repository);
        }

        for (TestLab testLab: testLabList) {
            transform(testLab, (SignavioDMNModelRepository) repository);
        }

        return new Pair<>(repository, testLabList);
    }

    private void transform(TestLab testLab, SignavioDMNModelRepository repository) {
        // Check for conflicts between the values of the InputData in the same equivalence class
        if (!forceMerge) {
            List<TestCase> testCases = testLab.getTestCases();
            for(TestCase testCase: testCases) {
                List<Expression> inputValues = testCase.getInputValues();
                for(int i=0; i<testLab.getInputParameterDefinitions().size()-1; i++) {
                    InputParameterDefinition firstParameter = testLab.getInputParameterDefinitions().get(i);
                    String firstRequirementName = equivalenceKey(firstParameter);
                    Expression firstExpression = inputValues.get(i);
                    for (int j = i + 1; j < testLab.getInputParameterDefinitions().size(); j++) {
                        InputParameterDefinition secondParameter = testLab.getInputParameterDefinitions().get(j);
                        String secondRequirementName = equivalenceKey(secondParameter);
                        if (firstRequirementName.equals(secondRequirementName)) {
                            Expression secondExpression = inputValues.get(j);
                            if (!Objects.equals(firstExpression, secondExpression)) {
                                throw new DMNRuntimeException(String.format("Cannot merge, incompatible values for InputData '%s' '%s' and '%s'", firstRequirementName, firstExpression, secondExpression));
                            }
                        }
                    }
                }
            }
        }

        // Calculate parameters to remove
        List<InputParameterDefinition> toRemove = new ArrayList<>();
        List<Integer> indexToRemove = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(int i=0; i<testLab.getInputParameterDefinitions().size(); i++) {
            InputParameterDefinition ipd = testLab.getInputParameterDefinitions().get(i);
            String requirementName = equivalenceKey(ipd);
            if (labels.contains(requirementName)) {
                toRemove.add(ipd);
                indexToRemove.add(i);
            } else {
                labels.add(requirementName);
                Pair<TInputData, List<TInputData>> pair = this.inputDataClasses.get(requirementName);
                if (pair == null) {
                    throw new DMNRuntimeException(String.format("Cannot find InputData for input parameter with requirementName='%s'", requirementName));
                }
                TInputData representative = pair.getLeft();
                QName diagramIdQName = repository.getDiagramIdQName();
                String representativeDiagramId = representative.getOtherAttributes().get(diagramIdQName);
                QName shapeIdQName = repository.getShapeIdQName();
                String representativeShapeId = representative.getOtherAttributes().get(shapeIdQName);
                ipd.setDiagramId(representativeDiagramId);
                ipd.setShapeId(representativeShapeId);
            }
        }

        // Remove InputParameterDefinition
        testLab.getInputParameterDefinitions().removeAll(toRemove);

        // Remove corresponding Input Values
        List<TestCase> testCases = testLab.getTestCases();
        for(TestCase testCase: testCases) {
            List<Expression> inputValues = testCase.getInputValues();
            List<Expression> newList = new ArrayList<>();
            for (int i=0; i<inputValues.size(); i++) {
                if (!indexToRemove.contains(i)) {
                    newList.add(inputValues.get(i));
                }
            }
            inputValues.clear();
            inputValues.addAll(newList);
        }
    }

    private DMNModelRepository mergeInputData(DMNModelRepository repository, BuildLogger logger) {
        // Compute equivalence classes for InputData with same label
        this.inputDataClasses = inputDataEquivalenceClasses(repository);

        // For each equivalence class
        TDefinitions definitions = repository.getRootDefinitions();
        for (Pair<TInputData, List<TInputData>> pair : inputDataClasses.values()) {
            TInputData representative = pair.getLeft();
            List<TInputData> inputDataInClass = pair.getRight();
            if (inputDataInClass.size() >= 2) {
                // For each decision
                List<TDecision> decisions = repository.findDecisions(definitions);
                for(TDecision decision: decisions) {
                    // Replace InputData in InformationRequirements with representative
                    List<TInformationRequirement> informationRequirementList = decision.getInformationRequirement();
                    for(TInformationRequirement ir: informationRequirementList) {
                        if (ir.getRequiredInput() != null) {
                            String href = ir.getRequiredInput().getHref();
                            TInputData referencedInputData = repository.findInputDataByRef(decision, href);
                            if (inputDataInClass.contains(referencedInputData) && referencedInputData != representative) {
                                String oldInputDataName = referencedInputData.getName();
                                String newInputDataName = representative.getName();
                                logger.info(String.format("Replacing input '%s' with '%s' in decision '%s'", oldInputDataName, newInputDataName, decision.getName()));

                                ir.getRequiredInput().setHref("#" + representative.getId());

                                // Replace in body
                                TExpression expression = decision.getExpression().getValue();
                                if (expression instanceof TDecisionTable) {
                                    // For each InputClause
                                    TDecisionTable decisionTable = (TDecisionTable) expression;
                                    List<TInputClause> inputClauselist = decisionTable.getInput();
                                    for(TInputClause ic: inputClauselist) {
                                        replace(oldInputDataName, newInputDataName, ic.getInputExpression());
                                    }
                                    // For each OutputClause
                                    for(TOutputClause oc: decisionTable.getOutput()) {
                                        replace(oldInputDataName, newInputDataName, oc.getDefaultOutputEntry());
                                    }
                                    // For each rule
                                    for(TDecisionRule rule: decisionTable.getRule()) {
                                        // Replace in description
                                        String description = rule.getDescription();
                                        rule.setDescription(replace(oldInputDataName, newInputDataName, description));
                                        // Replace in InputEntry
                                        for(TUnaryTests unaryTests : rule.getInputEntry()) {
                                            unaryTests.setText(replace(oldInputDataName, newInputDataName, unaryTests.getText()));
                                        }
                                        // Replace in OutputEntry
                                        for(TLiteralExpression literalExpression : rule.getOutputEntry()) {
                                            replace(oldInputDataName, newInputDataName, literalExpression);
                                        }
                                    }
                                } else if (expression instanceof TLiteralExpression) {
                                    replace(oldInputDataName, newInputDataName, (TLiteralExpression) expression);
                                }
                            }
                        }
                    }
                }
            }
        }

        // Remove all but the representative
        for(Pair<TInputData, List<TInputData>> pair : inputDataClasses.values()) {
            TInputData representative = pair.getLeft();
            List<TInputData> inputDataClass = pair.getRight();
            for (TInputData inputData : inputDataClass) {
                if (inputData != representative) {
                    removeDRGElement(definitions, inputData);
                }
            }
        }

        return repository;
    }

    private Map<String, Pair<TInputData, List<TInputData>>> inputDataEquivalenceClasses(DMNModelRepository dmnModelRepository) {
        Map<String, Pair<TInputData, List<TInputData>>> inputDataClasses = new LinkedHashMap<>();
        TDefinitions definitions = dmnModelRepository.getRootDefinitions();
        List<TInputData> inputDataList = dmnModelRepository.findInputDatas(definitions);
        for(TInputData inputData: inputDataList) {
            String key = equivalenceKey(inputData, dmnModelRepository);
            Pair<TInputData, List<TInputData>> inputDataClass = inputDataClasses.computeIfAbsent(key, k -> new Pair<>(null, new ArrayList<>()));
            inputDataClass.getRight().add(inputData);
        }
        for(Map.Entry<String, Pair<TInputData, List<TInputData>>> entry: inputDataClasses.entrySet()) {
            Pair<TInputData, List<TInputData>> inputDataClass = entry.getValue();
            TInputData representative = shortestName(inputDataClass.getRight());
            inputDataClasses.put(entry.getKey(), new Pair<>(representative, inputDataClass.getRight()));
        }

        return inputDataClasses;
    }

    private void replace(String oldName, String newName, TLiteralExpression expression) {
        if (expression != null) {
            String text = expression.getText();
            String newText = replace(oldName, newName, text);
            expression.setText(newText);
        }
    }

    private String replace(String oldName, String newName, String text) {
        return text == null ? null : text.replace(oldName, newName);
    }

    private void removeDRGElement(TDefinitions definitions, TDRGElement drgElement) {
        JAXBElement<?> elementToRemove = null;
        for(JAXBElement<?> element: definitions.getDrgElement()) {
            if (element.getValue() == drgElement) {
                elementToRemove = element;
            }
        }
        definitions.getDrgElement().remove(elementToRemove);
    }

    private TInputData shortestName(List<TInputData> inputDataClass) {
        int minLength = Integer.MAX_VALUE;
        TInputData representative = null;
        for(TInputData inputData: inputDataClass) {
            String name = inputData.getName();
            if (name.length() < minLength) {
                minLength = name.length();
                representative = inputData;
            }
        }
        return representative;
    }

    protected abstract String equivalenceKey(TInputData inputData, DMNModelRepository dmnModelRepository);

    protected abstract String equivalenceKey(InputParameterDefinition parameter);
}
