
import java.util.*
import java.util.stream.Collectors

@javax.annotation.Generated(value = ["signavio-decision.ftl", "makeCreditDecision"])
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "makeCreditDecision",
    label = "Make credit decision",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
    rulesCount = 3
)
class MakeCreditDecision(val compareAgainstLendingThreshold : CompareAgainstLendingThreshold = CompareAgainstLendingThreshold()) : com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision() {
    override fun apply(input_: MutableMap<String, String>, context_: com.gs.dmn.runtime.ExecutionContext): String? {
        try {
            return apply(input_.get("Applicant"), input_.get("Current risk appetite"), input_.get("Lending threshold"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache())
        } catch (e: Exception) {
            logError("Cannot apply decision 'MakeCreditDecision'", e)
            return null
        }
    }

    fun apply(applicant: String?, currentRiskAppetite: String?, lendingThreshold: String?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): String? {
        return try {
            apply(applicant?.let({ com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(it, object : com.fasterxml.jackson.core.type.TypeReference<type.ApplicantImpl>() {}) }), currentRiskAppetite?.let({ number(it) }), lendingThreshold?.let({ number(it) }), annotationSet_, eventListener_, externalExecutor_, cache_)
        } catch (e: Exception) {
            logError("Cannot apply decision 'MakeCreditDecision'", e)
            null
        }
    }

    fun apply(applicant: type.Applicant?, currentRiskAppetite: java.math.BigDecimal?, lendingThreshold: java.math.BigDecimal?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): String? {
        try {
            // Start decision 'makeCreditDecision'
            val makeCreditDecisionStartTime_ = System.currentTimeMillis()
            val makeCreditDecisionArguments_ = com.gs.dmn.runtime.listener.Arguments()
            makeCreditDecisionArguments_.put("Applicant", applicant)
            makeCreditDecisionArguments_.put("Current risk appetite", currentRiskAppetite)
            makeCreditDecisionArguments_.put("Lending threshold", lendingThreshold)
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, makeCreditDecisionArguments_)

            // Evaluate decision 'makeCreditDecision'
            val output_: String? = evaluate(applicant, currentRiskAppetite, lendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_)

            // End decision 'makeCreditDecision'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, makeCreditDecisionArguments_, output_, (System.currentTimeMillis() - makeCreditDecisionStartTime_))

            return output_
        } catch (e: Exception) {
            logError("Exception caught in 'makeCreditDecision' evaluation", e)
            return null
        }
    }

    fun apply(makeCreditDecisionRequest_: proto.MakeCreditDecisionRequest, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): proto.MakeCreditDecisionResponse {
        // Create arguments from Request Message
        val applicant: type.Applicant? = type.Applicant.toApplicant(makeCreditDecisionRequest_.getApplicant())
        val currentRiskAppetite: java.math.BigDecimal? = java.math.BigDecimal.valueOf(makeCreditDecisionRequest_.getCurrentRiskAppetite())
        val lendingThreshold: java.math.BigDecimal? = java.math.BigDecimal.valueOf(makeCreditDecisionRequest_.getLendingThreshold())

        // Invoke apply method
        val output_: String? = apply(applicant, currentRiskAppetite, lendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_)

        // Convert output to Response Message
        val builder_: proto.MakeCreditDecisionResponse.Builder = proto.MakeCreditDecisionResponse.newBuilder()
        val outputProto_ = (if (output_ == null) "" else output_!!)
        builder_.setMakeCreditDecision(outputProto_)
        return builder_.build()
    }

    private inline fun evaluate(applicant: type.Applicant?, currentRiskAppetite: java.math.BigDecimal?, lendingThreshold: java.math.BigDecimal?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): String? {
        // Apply child decisions
        val compareAgainstLendingThreshold: java.math.BigDecimal? = this.compareAgainstLendingThreshold.apply(applicant, currentRiskAppetite, lendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_)

        // Apply rules and collect results
        val ruleOutputList_ = com.gs.dmn.runtime.RuleOutputList()
        ruleOutputList_.add(rule0(compareAgainstLendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_))
        ruleOutputList_.add(rule1(compareAgainstLendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_))
        ruleOutputList_.add(rule2(compareAgainstLendingThreshold, annotationSet_, eventListener_, externalExecutor_, cache_))

        // Return results based on hit policy
        var output_: String?
        if (ruleOutputList_.noMatchedRules()) {
            // Default value
            output_ = null
        } else {
            val ruleOutput_: com.gs.dmn.runtime.RuleOutput? = ruleOutputList_.applySingle(com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE)
            output_ = ruleOutput_?.let({ (ruleOutput_ as MakeCreditDecisionRuleOutput).makeCreditDecision })
        }

        return output_
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 0, annotation = "\"\"")
    private fun rule0(compareAgainstLendingThreshold: java.math.BigDecimal?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): com.gs.dmn.runtime.RuleOutput {
        // Rule metadata
        val drgRuleMetadata: com.gs.dmn.runtime.listener.Rule = com.gs.dmn.runtime.listener.Rule(0, "\"\"")

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata)

        // Apply rule
        var output_: MakeCreditDecisionRuleOutput = MakeCreditDecisionRuleOutput(false)
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (numericLessThan(compareAgainstLendingThreshold, numericUnaryMinus(number("0.1"))))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata)

            // Compute output
            output_.setMatched(true)
            output_.makeCreditDecision = "Reject"

            // Add annotation
            annotationSet_.addAnnotation("makeCreditDecision", 0, "")
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_)

        return output_
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 1, annotation = "\"\"")
    private fun rule1(compareAgainstLendingThreshold: java.math.BigDecimal?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): com.gs.dmn.runtime.RuleOutput {
        // Rule metadata
        val drgRuleMetadata: com.gs.dmn.runtime.listener.Rule = com.gs.dmn.runtime.listener.Rule(1, "\"\"")

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata)

        // Apply rule
        var output_: MakeCreditDecisionRuleOutput = MakeCreditDecisionRuleOutput(false)
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (booleanAnd(numericGreaterEqualThan(compareAgainstLendingThreshold, numericUnaryMinus(number("0.1"))), numericLessEqualThan(compareAgainstLendingThreshold, number("0.1"))))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata)

            // Compute output
            output_.setMatched(true)
            output_.makeCreditDecision = "Recommend further assessment"

            // Add annotation
            annotationSet_.addAnnotation("makeCreditDecision", 1, "")
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_)

        return output_
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 2, annotation = "\"\"")
    private fun rule2(compareAgainstLendingThreshold: java.math.BigDecimal?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): com.gs.dmn.runtime.RuleOutput {
        // Rule metadata
        val drgRuleMetadata: com.gs.dmn.runtime.listener.Rule = com.gs.dmn.runtime.listener.Rule(2, "\"\"")

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata)

        // Apply rule
        var output_: MakeCreditDecisionRuleOutput = MakeCreditDecisionRuleOutput(false)
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (numericGreaterThan(compareAgainstLendingThreshold, number("0.1")))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata)

            // Compute output
            output_.setMatched(true)
            output_.makeCreditDecision = "Accept"

            // Add annotation
            annotationSet_.addAnnotation("makeCreditDecision", 2, "")
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_)

        return output_
    }


    companion object {
        val DRG_ELEMENT_METADATA : com.gs.dmn.runtime.listener.DRGElement = com.gs.dmn.runtime.listener.DRGElement(
            "",
            "makeCreditDecision",
            "Make credit decision",
            com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
            com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
            com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
            3
        )

        @JvmStatic
        fun requestToMap(makeCreditDecisionRequest_: proto.MakeCreditDecisionRequest): kotlin.collections.Map<String, Any?> {
            // Create arguments from Request Message
            val applicant: type.Applicant? = type.Applicant.toApplicant(makeCreditDecisionRequest_.getApplicant())
            val currentRiskAppetite: java.math.BigDecimal? = java.math.BigDecimal.valueOf(makeCreditDecisionRequest_.getCurrentRiskAppetite())
            val lendingThreshold: java.math.BigDecimal? = java.math.BigDecimal.valueOf(makeCreditDecisionRequest_.getLendingThreshold())

            // Create map
            val map_: kotlin.collections.MutableMap<String, Any?> = mutableMapOf()
            map_.put("Applicant", applicant)
            map_.put("Current risk appetite", currentRiskAppetite)
            map_.put("Lending threshold", lendingThreshold)
            return map_
        }

        @JvmStatic
        fun responseToOutput(makeCreditDecisionResponse_: proto.MakeCreditDecisionResponse): String? {
            // Extract and convert output
            return makeCreditDecisionResponse_.getMakeCreditDecision()
        }
    }
}
