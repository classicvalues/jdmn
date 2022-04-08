
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"bkm.ftl", "CreditContingencyFactorTable"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "CreditContingencyFactorTable",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
    rulesCount = 3
)
public class CreditContingencyFactorTable extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "CreditContingencyFactorTable",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
        com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
        com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
        3
    );

    private static class CreditContingencyFactorTableLazyHolder {
        static final CreditContingencyFactorTable INSTANCE = new CreditContingencyFactorTable();
    }
    public static CreditContingencyFactorTable instance() {
        return CreditContingencyFactorTableLazyHolder.INSTANCE;
    }

    private CreditContingencyFactorTable() {
    }

    public java.math.BigDecimal apply(String riskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start BKM 'CreditContingencyFactorTable'
            long creditContingencyFactorTableStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments creditContingencyFactorTableArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            creditContingencyFactorTableArguments_.put("RiskCategory", riskCategory);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, creditContingencyFactorTableArguments_);

            // Evaluate BKM 'CreditContingencyFactorTable'
            java.math.BigDecimal output_ = lambda.apply(riskCategory, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End BKM 'CreditContingencyFactorTable'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, creditContingencyFactorTableArguments_, output_, (System.currentTimeMillis() - creditContingencyFactorTableStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'CreditContingencyFactorTable' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> lambda =
        new com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal>() {
            public java.math.BigDecimal apply(Object... args_) {
                String riskCategory = 0 < args_.length ? (String) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                // Apply rules and collect results
                com.gs.dmn.runtime.RuleOutputList ruleOutputList_ = new com.gs.dmn.runtime.RuleOutputList();
                ruleOutputList_.add(rule0(riskCategory, annotationSet_, eventListener_, externalExecutor_, cache_));
                ruleOutputList_.add(rule1(riskCategory, annotationSet_, eventListener_, externalExecutor_, cache_));
                ruleOutputList_.add(rule2(riskCategory, annotationSet_, eventListener_, externalExecutor_, cache_));

                // Return results based on hit policy
                java.math.BigDecimal output_;
                if (ruleOutputList_.noMatchedRules()) {
                    // Default value
                    output_ = null;
                } else {
                    com.gs.dmn.runtime.RuleOutput ruleOutput_ = ruleOutputList_.applySingle(com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE);
                    output_ = ruleOutput_ == null ? null : ((CreditContingencyFactorTableRuleOutput)ruleOutput_).getCreditContingencyFactorTable();
                }

                return output_;
            }
    };

    @com.gs.dmn.runtime.annotation.Rule(index = 0, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule0(String riskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(0, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        CreditContingencyFactorTableRuleOutput output_ = new CreditContingencyFactorTableRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            booleanOr((stringEqual(riskCategory, "HIGH")), (stringEqual(riskCategory, "DECLINE")))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setCreditContingencyFactorTable(number("0.6"));

            // Add annotation
            annotationSet_.addAnnotation("CreditContingencyFactorTable", 0, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 1, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule1(String riskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(1, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        CreditContingencyFactorTableRuleOutput output_ = new CreditContingencyFactorTableRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (stringEqual(riskCategory, "MEDIUM"))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setCreditContingencyFactorTable(number("0.7"));

            // Add annotation
            annotationSet_.addAnnotation("CreditContingencyFactorTable", 1, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 2, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule2(String riskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(2, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        CreditContingencyFactorTableRuleOutput output_ = new CreditContingencyFactorTableRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            booleanOr((stringEqual(riskCategory, "LOW")), (stringEqual(riskCategory, "VERY LOW")))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setCreditContingencyFactorTable(number("0.8"));

            // Add annotation
            annotationSet_.addAnnotation("CreditContingencyFactorTable", 2, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

}
