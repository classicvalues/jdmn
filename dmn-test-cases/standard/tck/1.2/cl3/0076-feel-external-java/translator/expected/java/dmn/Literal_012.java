
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "literal_012"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "literal_012",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Literal_012 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "literal_012",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public Literal_012() {
    }

    @java.lang.Override()
    public Object apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'Literal_012'", e);
            return null;
        }
    }

    public Object apply(com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'literal_012'
            long literal_012StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments literal_012Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, literal_012Arguments_);

            // Evaluate decision 'literal_012'
            Object output_ = lambda.apply(annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'literal_012'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, literal_012Arguments_, output_, (System.currentTimeMillis() - literal_012StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'literal_012' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<Object> lambda =
        new com.gs.dmn.runtime.LambdaExpression<Object>() {
            public Object apply(Object... args_) {
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 0 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[0] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 1 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[1] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 2 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[2] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 3 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[3] : null;

                com.gs.dmn.runtime.external.JavaExternalFunction<Object> valueOf = new com.gs.dmn.runtime.external.JavaExternalFunction<>(new com.gs.dmn.runtime.external.JavaFunctionInfo("java.lang.Double", "valueOf", Arrays.asList("java.lang.String")), externalExecutor_, Object.class);
                return valueOf.apply("1234.56");
            }
        };
}
