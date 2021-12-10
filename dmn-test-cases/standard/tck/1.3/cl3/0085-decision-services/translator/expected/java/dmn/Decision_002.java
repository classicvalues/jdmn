
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "decision_002"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "decision_002",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Decision_002 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "decision_002",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private final Decision_002_input decision_002_input;

    public Decision_002() {
        this(new Decision_002_input());
    }

    public Decision_002(Decision_002_input decision_002_input) {
        this.decision_002_input = decision_002_input;
    }

    public String apply(com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        return apply(annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
    }

    public String apply(com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'decision_002'
            long decision_002StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments decision_002Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, decision_002Arguments_);

            // Apply child decisions
            String decision_002_input = this.decision_002_input.apply(annotationSet_, eventListener_, externalExecutor_, cache_);

            // Evaluate decision 'decision_002'
            String output_ = evaluate(decision_002_input, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'decision_002'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, decision_002Arguments_, output_, (System.currentTimeMillis() - decision_002StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'decision_002' evaluation", e);
            return null;
        }
    }

    protected String evaluate(String decision_002_input, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        return stringAdd("foo ", decision_002_input);
    }
}
