
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"signavio-decision.ftl", "decision"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "decision",
    label = "Decision",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.FIRST,
    rulesCount = 11
)
public class Decision extends com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "decision",
        "Decision",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
        com.gs.dmn.runtime.annotation.HitPolicy.FIRST,
        11
    );

    public Decision() {
    }

    @java.lang.Override()
    public String apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("BooleanInput"), input_.get("DateAndTimeInput"), input_.get("DateInput"), input_.get("EnumerationInput"), input_.get("NumberInput"), input_.get("TextInput"), input_.get("TimeInput"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'Decision'", e);
            return null;
        }
    }

    public String apply(String booleanInput, String dateAndTimeInput, String dateInput, String enumerationInput, String numberInput, String textInput, String timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((booleanInput != null ? Boolean.valueOf(booleanInput) : null), (dateAndTimeInput != null ? dateAndTime(dateAndTimeInput) : null), (dateInput != null ? date(dateInput) : null), enumerationInput, (numberInput != null ? number(numberInput) : null), textInput, (timeInput != null ? time(timeInput) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'Decision'", e);
            return null;
        }
    }

    public String apply(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'decision'
            long decisionStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments decisionArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            decisionArguments_.put("BooleanInput", booleanInput);
            decisionArguments_.put("DateAndTimeInput", dateAndTimeInput);
            decisionArguments_.put("DateInput", dateInput);
            decisionArguments_.put("EnumerationInput", enumerationInput);
            decisionArguments_.put("NumberInput", numberInput);
            decisionArguments_.put("TextInput", textInput);
            decisionArguments_.put("TimeInput", timeInput);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, decisionArguments_);

            // Evaluate decision 'decision'
            String output_ = evaluate(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'decision'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, decisionArguments_, output_, (System.currentTimeMillis() - decisionStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'decision' evaluation", e);
            return null;
        }
    }

    protected String evaluate(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Apply rules and collect results
        com.gs.dmn.runtime.RuleOutputList ruleOutputList_ = new com.gs.dmn.runtime.RuleOutputList();
        com.gs.dmn.runtime.RuleOutput tempRuleOutput_ = rule0(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
        ruleOutputList_.add(tempRuleOutput_);
        boolean matched_ = tempRuleOutput_.isMatched();
        if (!matched_) {
            tempRuleOutput_ = rule1(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule2(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule3(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule4(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule5(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule6(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule7(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule8(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule9(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }
        if (!matched_) {
            tempRuleOutput_ = rule10(booleanInput, dateAndTimeInput, dateInput, enumerationInput, numberInput, textInput, timeInput, annotationSet_, eventListener_, externalExecutor_, cache_);
            ruleOutputList_.add(tempRuleOutput_);
            matched_ = tempRuleOutput_.isMatched();
        }

        // Return results based on hit policy
        String output_;
        if (ruleOutputList_.noMatchedRules()) {
            // Default value
            output_ = null;
        } else {
            com.gs.dmn.runtime.RuleOutput ruleOutput_ = ruleOutputList_.applySingle(com.gs.dmn.runtime.annotation.HitPolicy.FIRST);
            output_ = ruleOutput_ == null ? null : ((DecisionRuleOutput)ruleOutput_).getDecision();
        }

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 0, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule0(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(0, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateEqual(dateInput, date("2016-08-01"))),
            (timeEqual(timeInput, time("12:00:00+0000"))),
            (dateTimeEqual(dateAndTimeInput, dateAndTime("2016-08-01T11:00:00+0000"))),
            (numericEqual(numberInput, numericUnaryMinus(number("1")))),
            (stringEqual(textInput, "abc")),
            (booleanEqual(booleanInput, Boolean.TRUE)),
            (stringEqual(enumerationInput, "e1"))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r1");

            // Add annotation
            annotationSet_.addAnnotation("decision", 0, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 1, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule1(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(1, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            booleanNot((dateEqual(dateInput, date("2016-08-01")))),
            booleanNot((timeEqual(timeInput, time("12:00:00+0000")))),
            booleanNot((dateTimeEqual(dateAndTimeInput, dateAndTime("2016-08-01T11:00:00+0000")))),
            booleanNot((numericEqual(numberInput, numericUnaryMinus(number("1"))))),
            booleanNot((stringEqual(textInput, "abc"))),
            booleanNot((booleanEqual(booleanInput, Boolean.FALSE))),
            booleanNot((stringEqual(enumerationInput, "e1")))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r2");

            // Add annotation
            annotationSet_.addAnnotation("decision", 1, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 2, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule2(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(2, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateLessThan(dateInput, date("2016-08-01"))),
            (timeLessThan(timeInput, time("12:00:00+0000"))),
            (dateTimeLessThan(dateAndTimeInput, dateAndTime("2016-08-01T11:00:00+0000"))),
            (numericLessThan(numberInput, numericUnaryMinus(number("1")))),
            (contains(textInput, "b")),
            Boolean.TRUE,
            booleanOr((stringEqual(enumerationInput, "e1")), (stringEqual(enumerationInput, "e2")))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r3");

            // Add annotation
            annotationSet_.addAnnotation("decision", 2, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 3, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule3(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(3, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateLessEqualThan(dateInput, date("2016-08-01"))),
            (timeLessEqualThan(timeInput, time("12:00:00+0000"))),
            (dateTimeLessEqualThan(dateAndTimeInput, dateAndTime("2016-08-01T11:00:00+0000"))),
            (numericGreaterEqualThan(numberInput, numericUnaryMinus(number("1")))),
            booleanNot((contains(textInput, "b"))),
            Boolean.TRUE,
            booleanNot(booleanOr((stringEqual(enumerationInput, "e1")), (stringEqual(enumerationInput, "e2"))))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r4");

            // Add annotation
            annotationSet_.addAnnotation("decision", 3, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 4, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule4(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(4, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateGreaterThan(dateInput, date("2016-08-01"))),
            (timeGreaterThan(timeInput, time("12:00:00+0000"))),
            (dateTimeGreaterThan(dateAndTimeInput, dateAndTime("2016-08-01T11:00:00+0000"))),
            (numericGreaterThan(numberInput, numericUnaryMinus(number("1")))),
            (startsWith(textInput, "ab")),
            Boolean.TRUE,
            Boolean.TRUE
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r5");

            // Add annotation
            annotationSet_.addAnnotation("decision", 4, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 5, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule5(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(5, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateGreaterEqualThan(dateInput, date("2016-08-01"))),
            (timeGreaterEqualThan(timeInput, time("12:00:00+0000"))),
            (dateTimeGreaterEqualThan(dateAndTimeInput, dateAndTime("2016-08-01T11:00:00+0000"))),
            (booleanAnd(numericGreaterEqualThan(numberInput, number("1")), numericLessThan(numberInput, number("2")))),
            (endsWith(textInput, "bc")),
            Boolean.TRUE,
            Boolean.TRUE
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r6");

            // Add annotation
            annotationSet_.addAnnotation("decision", 5, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 6, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule6(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(6, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            Boolean.TRUE,
            Boolean.TRUE,
            Boolean.TRUE,
            booleanNot((booleanAnd(numericGreaterEqualThan(numberInput, number("1")), numericLessThan(numberInput, number("2"))))),
            Boolean.TRUE,
            Boolean.TRUE,
            Boolean.TRUE
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r7");

            // Add annotation
            annotationSet_.addAnnotation("decision", 6, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 7, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule7(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(7, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            booleanNot((dateInput == null)),
            booleanNot((timeInput == null)),
            booleanNot((dateAndTimeInput == null)),
            booleanNot((numberInput == null)),
            booleanNot((textInput == null)),
            booleanNot((booleanInput == null)),
            booleanNot((enumerationInput == null))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r8");

            // Add annotation
            annotationSet_.addAnnotation("decision", 7, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 8, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule8(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(8, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateInput == null),
            (timeInput == null),
            (dateAndTimeInput == null),
            (numberInput == null),
            (textInput == null),
            (booleanInput == null),
            (enumerationInput == null)
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r9");

            // Add annotation
            annotationSet_.addAnnotation("decision", 8, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 9, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule9(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(9, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            booleanNot((dateInput == null)),
            booleanNot((timeInput == null)),
            booleanNot((dateAndTimeInput == null)),
            booleanNot((numberInput == null)),
            booleanNot((textInput == null)),
            booleanNot((booleanInput == null)),
            booleanNot((enumerationInput == null))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r10");

            // Add annotation
            annotationSet_.addAnnotation("decision", 9, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 10, annotation = "\"\"")
    public com.gs.dmn.runtime.RuleOutput rule10(Boolean booleanInput, javax.xml.datatype.XMLGregorianCalendar dateAndTimeInput, javax.xml.datatype.XMLGregorianCalendar dateInput, String enumerationInput, java.math.BigDecimal numberInput, String textInput, javax.xml.datatype.XMLGregorianCalendar timeInput, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(10, "\"\"");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DecisionRuleOutput output_ = new DecisionRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateInput == null),
            (timeInput == null),
            (dateAndTimeInput == null),
            (numberInput == null),
            (textInput == null),
            (booleanInput == null),
            (enumerationInput == null)
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDecision("r11");

            // Add annotation
            annotationSet_.addAnnotation("decision", 10, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

}
