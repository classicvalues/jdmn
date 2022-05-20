
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "literal_010"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "literal_010",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Literal_010 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "literal_010",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public Literal_010() {
    }

    public Object apply(com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'literal_010'
            long literal_010StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments literal_010Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, literal_010Arguments_);

            // Evaluate decision 'literal_010'
            Object output_ = lambda.apply(annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'literal_010'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, literal_010Arguments_, output_, (System.currentTimeMillis() - literal_010StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'literal_010' evaluation", e);
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

                com.gs.dmn.runtime.external.JavaExternalFunction<Object> valueOf = new com.gs.dmn.runtime.external.JavaExternalFunction<>(new com.gs.dmn.runtime.external.JavaFunctionInfo("java.lang.Integer", "valueOf", Arrays.asList("java.lang.String")), externalExecutor_, Object.class);
                return valueOf.apply("123");
            }
        };
}
