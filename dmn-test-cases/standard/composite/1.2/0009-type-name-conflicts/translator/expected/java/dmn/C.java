
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "c"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "c",
    label = "C",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class C extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "c",
        "C",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public C() {
    }

    public String apply(String aa, String ba, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        try {
            return apply((aa != null ? number(aa) : null), ba, annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'C'", e);
            return null;
        }
    }

    public String apply(String aa, String ba, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((aa != null ? number(aa) : null), ba, annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'C'", e);
            return null;
        }
    }

    public String apply(java.math.BigDecimal aa, String ba, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        return apply(aa, ba, annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
    }

    public String apply(java.math.BigDecimal aa, String ba, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'c'
            long cStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments cArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            cArguments_.put("AA", aa);
            cArguments_.put("BA", ba);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, cArguments_);

            // Evaluate decision 'c'
            String output_ = lambda.apply(aa, ba, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'c'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, cArguments_, output_, (System.currentTimeMillis() - cStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'c' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<String> lambda =
        new com.gs.dmn.runtime.LambdaExpression<String>() {
            public String apply(Object... args) {
                java.math.BigDecimal aa = 0 < args.length ? (java.math.BigDecimal) args[0] : null;
                String ba = 1 < args.length ? (String) args[1] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 2 < args.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args[2] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 3 < args.length ? (com.gs.dmn.runtime.listener.EventListener) args[3] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 4 < args.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args[4] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 5 < args.length ? (com.gs.dmn.runtime.cache.Cache) args[5] : null;

                return stringAdd(stringAdd(stringAdd("AA: ", string(aa)), "; BA: "), ba);
            }
        };
}
