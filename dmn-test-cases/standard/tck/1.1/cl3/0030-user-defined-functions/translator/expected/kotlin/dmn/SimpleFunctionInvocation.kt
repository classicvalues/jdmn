
import java.util.*
import java.util.stream.Collectors

@javax.annotation.Generated(value = ["decision.ftl", "'simple function invocation'"])
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "'simple function invocation'",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
class SimpleFunctionInvocation() : com.gs.dmn.runtime.DefaultDMNBaseDecision() {
    override fun apply(input_: MutableMap<String, String>, context_: com.gs.dmn.runtime.ExecutionContext): String? {
        try {
            return apply(input_.get("stringInputA"), input_.get("stringInputB"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache())
        } catch (e: Exception) {
            logError("Cannot apply decision 'SimpleFunctionInvocation'", e)
            return null
        }
    }

    fun apply(stringInputA: String?, stringInputB: String?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): String? {
        try {
            // Start decision ''simple function invocation''
            val simpleFunctionInvocationStartTime_ = System.currentTimeMillis()
            val simpleFunctionInvocationArguments_ = com.gs.dmn.runtime.listener.Arguments()
            simpleFunctionInvocationArguments_.put("stringInputA", stringInputA)
            simpleFunctionInvocationArguments_.put("stringInputB", stringInputB)
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, simpleFunctionInvocationArguments_)

            // Evaluate decision ''simple function invocation''
            val output_: String? = evaluate(stringInputA, stringInputB, annotationSet_, eventListener_, externalExecutor_, cache_)

            // End decision ''simple function invocation''
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, simpleFunctionInvocationArguments_, output_, (System.currentTimeMillis() - simpleFunctionInvocationStartTime_))

            return output_
        } catch (e: Exception) {
            logError("Exception caught in ''simple function invocation'' evaluation", e)
            return null
        }
    }

    private inline fun evaluate(stringInputA: String?, stringInputB: String?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): String? {
        val boxedFnDefinition: com.gs.dmn.runtime.LambdaExpression<String?>? = com.gs.dmn.runtime.LambdaExpression<String> { args_ -> val a: String? = args_[0] as String?; val b: String? = args_[1] as String?;stringAdd(a, b) } as com.gs.dmn.runtime.LambdaExpression<String?>?
        val literalFnDefinition: com.gs.dmn.runtime.LambdaExpression<String?>? = com.gs.dmn.runtime.LambdaExpression<String> { args_ -> val a: String? = args_[0] as String?; val b: String? = args_[1] as String?;stringAdd(a, b) } as com.gs.dmn.runtime.LambdaExpression<String?>?
        return stringAdd(boxedFnDefinition?.apply(stringInputA, stringInputB, annotationSet_, eventListener_, externalExecutor_, cache_), literalFnDefinition?.apply(stringInputA, stringInputB, annotationSet_, eventListener_, externalExecutor_, cache_))
    }

    companion object {
        val DRG_ELEMENT_METADATA : com.gs.dmn.runtime.listener.DRGElement = com.gs.dmn.runtime.listener.DRGElement(
            "",
            "'simple function invocation'",
            "",
            com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
            com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
            com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
            -1
        )
    }
}
