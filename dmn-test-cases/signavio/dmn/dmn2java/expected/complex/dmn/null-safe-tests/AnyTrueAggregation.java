
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"signavio-decision.ftl", "anyTrueAggregation"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "anyTrueAggregation",
    label = "anyTrueAggregation",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class AnyTrueAggregation extends com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "anyTrueAggregation",
        "anyTrueAggregation",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public AnyTrueAggregation() {
    }

    public Boolean apply(String booleanList, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((booleanList != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(booleanList, new com.fasterxml.jackson.core.type.TypeReference<List<Boolean>>() {}) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'AnyTrueAggregation'", e);
            return null;
        }
    }

    public Boolean apply(List<Boolean> booleanList, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'anyTrueAggregation'
            long anyTrueAggregationStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments anyTrueAggregationArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            anyTrueAggregationArguments_.put("booleanList", booleanList);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, anyTrueAggregationArguments_);

            // Iterate and aggregate
            Boolean output_ = evaluate(booleanList, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'anyTrueAggregation'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, anyTrueAggregationArguments_, output_, (System.currentTimeMillis() - anyTrueAggregationStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'anyTrueAggregation' evaluation", e);
            return null;
        }
    }

    protected Boolean evaluate(List<Boolean> booleanList, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        KeepInputanyTrue keepInputanyTrue = new KeepInputanyTrue();
        return booleanList.stream().anyMatch(booleanAnyTrue_iterator -> keepInputanyTrue.apply(booleanAnyTrue_iterator, annotationSet_, eventListener_, externalExecutor_, cache_));
    }
}
