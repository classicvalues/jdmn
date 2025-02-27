
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "payment"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "payment",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Payment extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "payment",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public Payment() {
    }

    @java.lang.Override()
    public java.math.BigDecimal apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("loan"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'Payment'", e);
            return null;
        }
    }

    public java.math.BigDecimal apply(String loan, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((loan != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(loan, new com.fasterxml.jackson.core.type.TypeReference<type.TLoanImpl>() {}) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'Payment'", e);
            return null;
        }
    }

    public java.math.BigDecimal apply(type.TLoan loan, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'payment'
            long paymentStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments paymentArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            paymentArguments_.put("loan", loan);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, paymentArguments_);

            // Evaluate decision 'payment'
            java.math.BigDecimal output_ = lambda.apply(loan, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'payment'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, paymentArguments_, output_, (System.currentTimeMillis() - paymentStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'payment' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> lambda =
        new com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal>() {
            public java.math.BigDecimal apply(Object... args_) {
                type.TLoan loan = 0 < args_.length ? (type.TLoan) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                return numericDivide(numericDivide(numericMultiply(((java.math.BigDecimal)(loan != null ? loan.getPrincipal() : null)), ((java.math.BigDecimal)(loan != null ? loan.getRate() : null))), number("12")), numericSubtract(number("1"), numericExponentiation(numericAdd(number("1"), numericDivide(((java.math.BigDecimal)(loan != null ? loan.getRate() : null)), number("12"))), numericUnaryMinus(((java.math.BigDecimal)(loan != null ? loan.getTermMonths() : null))))));
            }
        };
}
