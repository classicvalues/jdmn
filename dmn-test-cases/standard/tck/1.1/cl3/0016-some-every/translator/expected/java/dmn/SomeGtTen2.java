
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "someGtTen2"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "someGtTen2",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class SomeGtTen2 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "someGtTen2",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public SomeGtTen2() {
    }

    @java.lang.Override()
    public Boolean apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("priceTable2"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'SomeGtTen2'", e);
            return null;
        }
    }

    public Boolean apply(String priceTable2, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((priceTable2 != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(priceTable2, new com.fasterxml.jackson.core.type.TypeReference<List<type.TItemPrice>>() {}) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'SomeGtTen2'", e);
            return null;
        }
    }

    public Boolean apply(List<type.TItemPrice> priceTable2, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'someGtTen2'
            long someGtTen2StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments someGtTen2Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            someGtTen2Arguments_.put("priceTable2", priceTable2);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, someGtTen2Arguments_);

            // Evaluate decision 'someGtTen2'
            Boolean output_ = lambda.apply(priceTable2, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'someGtTen2'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, someGtTen2Arguments_, output_, (System.currentTimeMillis() - someGtTen2StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'someGtTen2' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<Boolean> lambda =
        new com.gs.dmn.runtime.LambdaExpression<Boolean>() {
            public Boolean apply(Object... args_) {
                List<type.TItemPrice> priceTable2 = 0 < args_.length ? (List<type.TItemPrice>) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                return booleanOr((List)priceTable2.stream().map(i -> numericGreaterThan(((java.math.BigDecimal)(i != null ? i.getPrice() : null)), number("10"))).collect(Collectors.toList()));
            }
        };
}
