
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"signavio-decision.ftl", "DateTime"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "DateTime",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class DateTime extends com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "DateTime",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public static java.util.Map<String, Object> requestToMap(proto.DateTimeRequest dateTimeRequest_) {
        // Create arguments from Request Message
        type.TCompositeDateTime compositeInputDateTime = type.TCompositeDateTime.toTCompositeDateTime(dateTimeRequest_.getCompositeInputDateTime());
        javax.xml.datatype.XMLGregorianCalendar inputDate = com.gs.dmn.signavio.feel.lib.DefaultSignavioLib.INSTANCE.date(dateTimeRequest_.getInputDate());
        javax.xml.datatype.XMLGregorianCalendar inputDateTime = com.gs.dmn.signavio.feel.lib.DefaultSignavioLib.INSTANCE.dateAndTime(dateTimeRequest_.getInputDateTime());
        javax.xml.datatype.XMLGregorianCalendar inputTime = com.gs.dmn.signavio.feel.lib.DefaultSignavioLib.INSTANCE.time(dateTimeRequest_.getInputTime());

        // Create map
        java.util.Map<String, Object> map_ = new java.util.LinkedHashMap<>();
        map_.put("CompositeInputDateTime", compositeInputDateTime);
        map_.put("InputDate", inputDate);
        map_.put("InputDateTime", inputDateTime);
        map_.put("InputTime", inputTime);
        return map_;
    }

    public static javax.xml.datatype.XMLGregorianCalendar responseToOutput(proto.DateTimeResponse dateTimeResponse_) {
        // Extract and convert output
        return com.gs.dmn.signavio.feel.lib.DefaultSignavioLib.INSTANCE.dateAndTime(dateTimeResponse_.getDateTime());
    }

    public DateTime() {
    }

    @java.lang.Override()
    public javax.xml.datatype.XMLGregorianCalendar apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("CompositeInputDateTime"), input_.get("InputDate"), input_.get("InputDateTime"), input_.get("InputTime"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'DateTime'", e);
            return null;
        }
    }

    public javax.xml.datatype.XMLGregorianCalendar apply(String compositeInputDateTime, String inputDate, String inputDateTime, String inputTime, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((compositeInputDateTime != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(compositeInputDateTime, new com.fasterxml.jackson.core.type.TypeReference<type.TCompositeDateTimeImpl>() {}) : null), (inputDate != null ? date(inputDate) : null), (inputDateTime != null ? dateAndTime(inputDateTime) : null), (inputTime != null ? time(inputTime) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'DateTime'", e);
            return null;
        }
    }

    public javax.xml.datatype.XMLGregorianCalendar apply(type.TCompositeDateTime compositeInputDateTime, javax.xml.datatype.XMLGregorianCalendar inputDate, javax.xml.datatype.XMLGregorianCalendar inputDateTime, javax.xml.datatype.XMLGregorianCalendar inputTime, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'DateTime'
            long dateTimeStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments dateTimeArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            dateTimeArguments_.put("CompositeInputDateTime", compositeInputDateTime);
            dateTimeArguments_.put("InputDate", inputDate);
            dateTimeArguments_.put("InputDateTime", inputDateTime);
            dateTimeArguments_.put("InputTime", inputTime);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, dateTimeArguments_);

            // Evaluate decision 'DateTime'
            javax.xml.datatype.XMLGregorianCalendar output_ = evaluate(compositeInputDateTime, inputDate, inputDateTime, inputTime, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'DateTime'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, dateTimeArguments_, output_, (System.currentTimeMillis() - dateTimeStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'DateTime' evaluation", e);
            return null;
        }
    }

    public proto.DateTimeResponse apply(proto.DateTimeRequest dateTimeRequest_, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Create arguments from Request Message
        type.TCompositeDateTime compositeInputDateTime = type.TCompositeDateTime.toTCompositeDateTime(dateTimeRequest_.getCompositeInputDateTime());
        javax.xml.datatype.XMLGregorianCalendar inputDate = date(dateTimeRequest_.getInputDate());
        javax.xml.datatype.XMLGregorianCalendar inputDateTime = dateAndTime(dateTimeRequest_.getInputDateTime());
        javax.xml.datatype.XMLGregorianCalendar inputTime = time(dateTimeRequest_.getInputTime());

        // Invoke apply method
        javax.xml.datatype.XMLGregorianCalendar output_ = apply(compositeInputDateTime, inputDate, inputDateTime, inputTime, annotationSet_, eventListener_, externalExecutor_, cache_);

        // Convert output to Response Message
        proto.DateTimeResponse.Builder builder_ = proto.DateTimeResponse.newBuilder();
        String outputProto_ = string(output_);
        builder_.setDateTime(outputProto_);
        return builder_.build();
    }

    protected javax.xml.datatype.XMLGregorianCalendar evaluate(type.TCompositeDateTime compositeInputDateTime, javax.xml.datatype.XMLGregorianCalendar inputDate, javax.xml.datatype.XMLGregorianCalendar inputDateTime, javax.xml.datatype.XMLGregorianCalendar inputTime, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        return inputDateTime;
    }
}
