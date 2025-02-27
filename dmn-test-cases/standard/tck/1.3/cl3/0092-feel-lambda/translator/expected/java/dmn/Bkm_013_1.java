
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"bkm.ftl", "bkm_013_1"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "bkm_013_1",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Bkm_013_1 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "bkm_013_1",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private static class Bkm_013_1LazyHolder {
        static final Bkm_013_1 INSTANCE = new Bkm_013_1();
    }
    public static Bkm_013_1 instance() {
        return Bkm_013_1LazyHolder.INSTANCE;
    }

    private Bkm_013_1() {
    }

    @java.lang.Override()
    public java.math.BigDecimal apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        throw new com.gs.dmn.runtime.DMNRuntimeException("Not all arguments can be serialized");
    }

    public java.math.BigDecimal apply(com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> fn1, com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> fn2, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start BKM 'bkm_013_1'
            long bkm_013_1StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments bkm_013_1Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            bkm_013_1Arguments_.put("fn1", fn1);
            bkm_013_1Arguments_.put("fn2", fn2);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, bkm_013_1Arguments_);

            // Evaluate BKM 'bkm_013_1'
            java.math.BigDecimal output_ = lambda.apply(fn1, fn2, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End BKM 'bkm_013_1'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, bkm_013_1Arguments_, output_, (System.currentTimeMillis() - bkm_013_1StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'bkm_013_1' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> lambda =
        new com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal>() {
            public java.math.BigDecimal apply(Object... args_) {
                com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> fn1 = 0 < args_.length ? (com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal>) args_[0] : null;
                com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> fn2 = 1 < args_.length ? (com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal>) args_[1] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 2 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[2] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 3 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[3] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 4 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[4] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 5 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[5] : null;

                return numericMultiply(fn1.apply(number("5"), annotationSet_, eventListener_, externalExecutor_, cache_), fn2.apply(number("10"), annotationSet_, eventListener_, externalExecutor_, cache_));
            }
        };
}
