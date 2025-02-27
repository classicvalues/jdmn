
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"signavio-bkm.ftl", "importedLogicTime"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "importedLogicTime",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class ImportedLogicTime extends com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "importedLogicTime",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
        com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private static class ImportedLogicTimeLazyHolder {
        static final ImportedLogicTime INSTANCE = new ImportedLogicTime();
    }
    public static ImportedLogicTime instance() {
        return ImportedLogicTimeLazyHolder.INSTANCE;
    }

    private ImportedLogicTime() {
    }

    @java.lang.Override()
    public List<String> apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply((input_.get("time") != null ? time(input_.get("time")) : null), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'ImportedLogicTime'", e);
            return null;
        }
    }

    public List<String> apply(javax.xml.datatype.XMLGregorianCalendar time, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start BKM 'importedLogicTime'
            long importedLogicTimeStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments importedLogicTimeArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            importedLogicTimeArguments_.put("time", time);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, importedLogicTimeArguments_);

            // Evaluate BKM 'importedLogicTime'
            List<String> output_ = evaluate(time, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End BKM 'importedLogicTime'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, importedLogicTimeArguments_, output_, (System.currentTimeMillis() - importedLogicTimeStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'importedLogicTime' evaluation", e);
            return null;
        }
    }

    protected List<String> evaluate(javax.xml.datatype.XMLGregorianCalendar time, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        return new TimeOperators().apply(time, annotationSet_, eventListener_, externalExecutor_, cache_);
    }
}
