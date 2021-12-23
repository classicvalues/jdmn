
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "Adjudication"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "Adjudication",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Adjudication extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "Adjudication",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public Adjudication() {
    }

    public String apply(String applicantData, String bureauData, String supportingDocuments, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        try {
            return apply((applicantData != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(applicantData, new com.fasterxml.jackson.core.type.TypeReference<type.TApplicantDataImpl>() {}) : null), (bureauData != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(bureauData, new com.fasterxml.jackson.core.type.TypeReference<type.TBureauDataImpl>() {}) : null), supportingDocuments, annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'Adjudication'", e);
            return null;
        }
    }

    public String apply(String applicantData, String bureauData, String supportingDocuments, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((applicantData != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(applicantData, new com.fasterxml.jackson.core.type.TypeReference<type.TApplicantDataImpl>() {}) : null), (bureauData != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(bureauData, new com.fasterxml.jackson.core.type.TypeReference<type.TBureauDataImpl>() {}) : null), supportingDocuments, annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'Adjudication'", e);
            return null;
        }
    }

    public String apply(type.TApplicantData applicantData, type.TBureauData bureauData, String supportingDocuments, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        return apply(applicantData, bureauData, supportingDocuments, annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
    }

    public String apply(type.TApplicantData applicantData, type.TBureauData bureauData, String supportingDocuments, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'Adjudication'
            long adjudicationStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments adjudicationArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            adjudicationArguments_.put("ApplicantData", applicantData);
            adjudicationArguments_.put("BureauData", bureauData);
            adjudicationArguments_.put("SupportingDocuments", supportingDocuments);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, adjudicationArguments_);

            // Evaluate decision 'Adjudication'
            String output_ = lambda.apply(applicantData, bureauData, supportingDocuments, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'Adjudication'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, adjudicationArguments_, output_, (System.currentTimeMillis() - adjudicationStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'Adjudication' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<String> lambda =
        new com.gs.dmn.runtime.LambdaExpression<String>() {
            public String apply(Object... args) {
                type.TApplicantData applicantData = 0 < args.length ? (type.TApplicantData) args[0] : null;
                type.TBureauData bureauData = 1 < args.length ? (type.TBureauData) args[1] : null;
                String supportingDocuments = 2 < args.length ? (String) args[2] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 3 < args.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args[3] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 4 < args.length ? (com.gs.dmn.runtime.listener.EventListener) args[4] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 5 < args.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args[5] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 6 < args.length ? (com.gs.dmn.runtime.cache.Cache) args[6] : null;

                return "ACCEPT";
            }
        };
}
