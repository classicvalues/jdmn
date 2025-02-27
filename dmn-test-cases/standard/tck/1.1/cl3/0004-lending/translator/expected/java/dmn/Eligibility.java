
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "Eligibility"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "Eligibility",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.INVOCATION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Eligibility extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "Eligibility",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.INVOCATION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private final PreBureauAffordability preBureauAffordability;
    private final PreBureauRiskCategory preBureauRiskCategory;

    public Eligibility() {
        this(new PreBureauAffordability(), new PreBureauRiskCategory());
    }

    public Eligibility(PreBureauAffordability preBureauAffordability, PreBureauRiskCategory preBureauRiskCategory) {
        this.preBureauAffordability = preBureauAffordability;
        this.preBureauRiskCategory = preBureauRiskCategory;
    }

    @java.lang.Override()
    public String apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("ApplicantData"), input_.get("RequestedProduct"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'Eligibility'", e);
            return null;
        }
    }

    public String apply(String applicantData, String requestedProduct, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((applicantData != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(applicantData, new com.fasterxml.jackson.core.type.TypeReference<type.TApplicantDataImpl>() {}) : null), (requestedProduct != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(requestedProduct, new com.fasterxml.jackson.core.type.TypeReference<type.TRequestedProductImpl>() {}) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'Eligibility'", e);
            return null;
        }
    }

    public String apply(type.TApplicantData applicantData, type.TRequestedProduct requestedProduct, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'Eligibility'
            long eligibilityStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments eligibilityArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            eligibilityArguments_.put("ApplicantData", applicantData);
            eligibilityArguments_.put("RequestedProduct", requestedProduct);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, eligibilityArguments_);

            // Evaluate decision 'Eligibility'
            String output_ = lambda.apply(applicantData, requestedProduct, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'Eligibility'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, eligibilityArguments_, output_, (System.currentTimeMillis() - eligibilityStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'Eligibility' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<String> lambda =
        new com.gs.dmn.runtime.LambdaExpression<String>() {
            public String apply(Object... args_) {
                type.TApplicantData applicantData = 0 < args_.length ? (type.TApplicantData) args_[0] : null;
                type.TRequestedProduct requestedProduct = 1 < args_.length ? (type.TRequestedProduct) args_[1] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 2 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[2] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 3 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[3] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 4 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[4] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 5 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[5] : null;

                // Apply child decisions
                Boolean preBureauAffordability = Eligibility.this.preBureauAffordability.apply(applicantData, requestedProduct, annotationSet_, eventListener_, externalExecutor_, cache_);
                String preBureauRiskCategory = Eligibility.this.preBureauRiskCategory.apply(applicantData, annotationSet_, eventListener_, externalExecutor_, cache_);

                return EligibilityRules.instance().apply(preBureauRiskCategory, preBureauAffordability, ((java.math.BigDecimal)(applicantData != null ? applicantData.getAge() : null)), annotationSet_, eventListener_, externalExecutor_, cache_);
            }
        };
}
