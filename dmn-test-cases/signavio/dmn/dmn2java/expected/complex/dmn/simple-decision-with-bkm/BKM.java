
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"signavio-bkm.ftl", "bKM"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "bKM",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class BKM extends com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "bKM",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
        com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private static class BKMLazyHolder {
        static final BKM INSTANCE = new BKM();
    }
    public static BKM instance() {
        return BKMLazyHolder.INSTANCE;
    }

    private BKM() {
    }

    @java.lang.Override()
    public java.math.BigDecimal apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply((input_.get("a") != null ? number(input_.get("a")) : null), (input_.get("b") != null ? number(input_.get("b")) : null), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'BKM'", e);
            return null;
        }
    }

    public java.math.BigDecimal apply(java.math.BigDecimal a, java.math.BigDecimal b, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start BKM 'bKM'
            long bKMStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments bKMArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            bKMArguments_.put("A", a);
            bKMArguments_.put("B", b);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, bKMArguments_);

            // Evaluate BKM 'bKM'
            java.math.BigDecimal output_ = evaluate(a, b, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End BKM 'bKM'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, bKMArguments_, output_, (System.currentTimeMillis() - bKMStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'bKM' evaluation", e);
            return null;
        }
    }

    protected java.math.BigDecimal evaluate(java.math.BigDecimal a, java.math.BigDecimal b, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        return new SUM().apply(a, b, annotationSet_, eventListener_, externalExecutor_, cache_);
    }
}
