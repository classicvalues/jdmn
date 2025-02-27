
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"signavio-decision.ftl", "generateOutputData"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "generateOutputData",
    label = "Generate output data",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class GenerateOutputData extends com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "generateOutputData",
        "Generate output data",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private static class GenerateOutputDataLazyHolder {
        static final GenerateOutputData INSTANCE = new GenerateOutputData(AssessIssueRisk.instance(), CompareAgainstLendingThreshold.instance(), MakeCreditDecision.instance());
    }
    public static GenerateOutputData instance() {
        return GenerateOutputDataLazyHolder.INSTANCE;
    }

    private final AssessIssueRisk assessIssueRisk;
    private final CompareAgainstLendingThreshold compareAgainstLendingThreshold;
    private final MakeCreditDecision makeCreditDecision;

    public GenerateOutputData() {
        this(new AssessIssueRisk(), new CompareAgainstLendingThreshold(), new MakeCreditDecision());
    }

    public GenerateOutputData(AssessIssueRisk assessIssueRisk, CompareAgainstLendingThreshold compareAgainstLendingThreshold, MakeCreditDecision makeCreditDecision) {
        this.assessIssueRisk = assessIssueRisk;
        this.compareAgainstLendingThreshold = compareAgainstLendingThreshold;
        this.makeCreditDecision = makeCreditDecision;
    }

    @java.lang.Override()
    public List<type.GenerateOutputData> apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("Applicant"), input_.get("Current risk appetite"), input_.get("Lending threshold"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'GenerateOutputData'", e);
            return null;
        }
    }

    public List<type.GenerateOutputData> apply(String applicant, String currentRiskAppetite, String lendingThreshold, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((applicant != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(applicant, new com.fasterxml.jackson.core.type.TypeReference<type.ApplicantImpl>() {}) : null), (currentRiskAppetite != null ? number(currentRiskAppetite) : null), (lendingThreshold != null ? number(lendingThreshold) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'GenerateOutputData'", e);
            return null;
        }
    }

    public List<type.GenerateOutputData> apply(type.Applicant applicant, java.math.BigDecimal currentRiskAppetite, java.math.BigDecimal lendingThreshold, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'generateOutputData'
            long generateOutputDataStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments generateOutputDataArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            generateOutputDataArguments_.put("Applicant", applicant);
            generateOutputDataArguments_.put("Current risk appetite", currentRiskAppetite);
            generateOutputDataArguments_.put("Lending threshold", lendingThreshold);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, generateOutputDataArguments_);

            // Evaluate decision 'generateOutputData'
            List<type.GenerateOutputData> output_ = evaluate(applicant, currentRiskAppetite, lendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'generateOutputData'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, generateOutputDataArguments_, output_, (System.currentTimeMillis() - generateOutputDataStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'generateOutputData' evaluation", e);
            return null;
        }
    }

    protected List<type.GenerateOutputData> evaluate(type.Applicant applicant, java.math.BigDecimal currentRiskAppetite, java.math.BigDecimal lendingThreshold, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Apply child decisions
        java.math.BigDecimal assessIssueRisk = this.assessIssueRisk.apply(applicant, currentRiskAppetite, annotationSet_, eventListener_, externalExecutor_, cache_);
        java.math.BigDecimal compareAgainstLendingThreshold = this.compareAgainstLendingThreshold.apply(applicant, currentRiskAppetite, lendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_);
        String makeCreditDecision = this.makeCreditDecision.apply(applicant, currentRiskAppetite, lendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_);

        return zip(asList("Decision", "Assessment", "Issue"), asList(asList(makeCreditDecision), asList(compareAgainstLendingThreshold), asList(assessIssueRisk))).stream().map(x -> type.GenerateOutputData.toGenerateOutputData(x)).collect(Collectors.toList());
    }
}
