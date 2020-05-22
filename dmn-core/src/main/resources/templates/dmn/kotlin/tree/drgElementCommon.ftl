<#--
    Copyright 2016 Goldman Sachs.

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

    You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations under the License.
-->
<#--
    Apply method body
-->
<#macro applyMethodBody drgElement>
        try {
        <@startDRGElement drgElement/>

        <#if modelRepository.isDecisionTableExpression(drgElement)>
            <@expressionApplyBody drgElement />
        <#elseif modelRepository.isLiteralExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#elseif modelRepository.isInvocationExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#elseif modelRepository.isContextExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#elseif modelRepository.isRelationExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#else >
            logError("${modelRepository.expression(drgElement).class.simpleName} is not implemented yet")
            return null
        </#if>
        } catch (e: Exception) {
            logError("Exception caught in '${modelRepository.name(drgElement)}' evaluation", e)
            return null
        }
</#macro>

<#---
    Evaluate method
-->
<#macro evaluateExpressionMethod drgElement>
    <#if modelRepository.isDecisionTableExpression(drgElement)>

        <@addEvaluateDecisionTableMethod drgElement/>
        <@addRuleMethods drgElement/>
        <@addConversionMethod drgElement/>
    <#elseif modelRepository.isLiteralExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    <#elseif modelRepository.isInvocationExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    <#elseif modelRepository.isContextExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    <#elseif modelRepository.isRelationExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    </#if>
</#macro>

<#--
    Decision table
-->
<#macro addEvaluateDecisionTableMethod drgElement>
    private fun evaluate(${transformer.drgElementEvaluateSignature(drgElement)}): ${transformer.drgElementOutputType(drgElement)} {
    <#assign expression = modelRepository.expression(drgElement)>
        <@collectRuleResults drgElement expression />

        // Return results based on hit policy
        var output_: ${transformer.drgElementOutputType(drgElement)}
    <#if modelRepository.isSingleHit(expression.hitPolicy)>
        if (ruleOutputList_.noMatchedRules()) {
            // Default value
            output_ = ${transformer.defaultValue(drgElement)}
        } else {
            val ruleOutput_: ${transformer.abstractRuleOutputClassName()}? = ruleOutputList_.applySingle(${transformer.hitPolicyAnnotationClassName()}.${transformer.hitPolicy(drgElement)})
            <#if modelRepository.isCompoundDecisionTable(drgElement)>
            output_ = toDecisionOutput(ruleOutput_ as ${transformer.ruleOutputClassName(drgElement)})
            <#else>
            output_ = ruleOutput_?.let({ (ruleOutput_ as ${transformer.ruleOutputClassName(drgElement)}).${transformer.outputClauseVariableName(drgElement, expression.output[0])} })
            </#if>
        }

        return output_
    <#elseif modelRepository.isMultipleHit(expression.hitPolicy)>
        if (ruleOutputList_.noMatchedRules()) {
            // Default value
            output_ = ${transformer.defaultValue(drgElement)}
        } else {
            val ruleOutputs_: List<${transformer.abstractRuleOutputClassName()}> = ruleOutputList_.applyMultiple(${transformer.hitPolicyAnnotationClassName()}.${transformer.hitPolicy(drgElement)})
        <#if modelRepository.isCompoundDecisionTable(drgElement)>
            <#if modelRepository.hasAggregator(expression)>
            output_ = null
            <#else>
            output_ = ruleOutputs_?.stream().map({ o -> toDecisionOutput(o as ${transformer.ruleOutputClassName(drgElement)}) }).collect(Collectors.toList())
            </#if>
        <#else >
            <#if modelRepository.hasAggregator(expression)>
            output_ = ${transformer.aggregator(drgElement, expression, expression.output[0], "ruleOutputs_")}
            <#else>
            output_ = ruleOutputs_.stream().map({ o -> (o as ${transformer.ruleOutputClassName(drgElement)}).${transformer.outputClauseVariableName(drgElement, expression.output[0])} }).collect(Collectors.toList())
            </#if>
        </#if>
        }

        return output_
    <#else>
        logError("Unknown hit policy '" + ${expression.hitPolicy} + "'"))
        return output_
    </#if>
    }

</#macro>

<#macro addRuleMethods drgElement>
    <#assign expression = modelRepository.expression(drgElement)>
    <#list expression.rule>
        <#items as rule>
    @${transformer.ruleAnnotationClassName()}(index = ${rule_index}, annotation = "${transformer.annotationEscapedText(rule)}")
    private fun rule${rule_index}(${transformer.drgElementSignatureExtra(transformer.ruleSignature(drgElement))}): ${transformer.abstractRuleOutputClassName()} {
        // Rule metadata
        val ${transformer.drgRuleMetadataFieldName()}: ${transformer.drgRuleMetadataClassName()} = ${transformer.drgRuleMetadataClassName()}(${rule_index}, "${transformer.annotationEscapedText(rule)}")

        <@startRule drgElement rule_index />

        // Apply rule
        var output_: ${transformer.ruleOutputClassName(drgElement)} = ${transformer.ruleOutputClassName(drgElement)}(false)
        if (${transformer.condition(drgElement, rule)}) {
            <@matchRule drgElement rule_index />

            // Compute output
            output_.setMatched(true)
            <#list expression.output as output>
            output_.${transformer.outputClauseVariableName(drgElement, output)} = ${transformer.outputEntryToJava(drgElement, rule.outputEntry[output_index], output_index)}
                <#if modelRepository.isOutputOrderHit(expression.hitPolicy) && transformer.priority(drgElement, rule.outputEntry[output_index], output_index)?exists>
            output_.${transformer.outputClausePriorityVariableName(drgElement, output)} = ${transformer.priority(drgElement, rule.outputEntry[output_index], output_index)}
                </#if>
            </#list>

            <@addAnnotation drgElement rule rule_index />
        }

        <@endRule drgElement rule_index "output_" />

        return output_
    }

        </#items>
    </#list>
</#macro>

<#macro collectRuleResults drgElement expression>
        // Apply rules and collect results
        val ruleOutputList_ = ${transformer.ruleOutputListClassName()}()
    <#assign expression = modelRepository.expression(drgElement)>
    <#list expression.rule>
        <#items as rule>
        <#if modelRepository.isFirstSingleHit(expression.hitPolicy) && modelRepository.atLeastTwoRules(expression)>
        <#if rule?is_first>
        var tempRuleOutput_: ${transformer.abstractRuleOutputClassName()} = rule${rule_index}(${transformer.drgElementArgumentsExtra(transformer.ruleArgumentList(drgElement))})
        ruleOutputList_.add(tempRuleOutput_)
        var matched_: Boolean = tempRuleOutput_.isMatched()
        <#else >
        if (!matched_) {
            tempRuleOutput_ = rule${rule_index}(${transformer.drgElementArgumentsExtra(transformer.ruleArgumentList(drgElement))})
            ruleOutputList_.add(tempRuleOutput_)
            matched_ = tempRuleOutput_.isMatched()
        }
        </#if>
        <#else >
        ruleOutputList_.add(rule${rule_index}(${transformer.drgElementArgumentsExtra(transformer.ruleArgumentList(drgElement))}))
        </#if>
        </#items>
    </#list>
</#macro>

<#macro addConversionMethod drgElement>
    <#if modelRepository.isCompoundDecisionTable(drgElement)>
    fun toDecisionOutput(ruleOutput_: ${transformer.ruleOutputClassName(drgElement)}): ${transformer.drgElementOutputClassName(drgElement)} {
        val result_: ${transformer.itemDefinitionJavaClassName(transformer.drgElementOutputClassName(drgElement))} = ${transformer.defaultConstructor(transformer.itemDefinitionJavaClassName(transformer.drgElementOutputClassName(drgElement)))}
        <#assign expression = modelRepository.expression(drgElement)>
        <#list expression.output as output>
        result_.${transformer.outputClauseVariableName(drgElement, output)} = ruleOutput_.let({ it.${transformer.outputClauseVariableName(drgElement, output)} })
        </#list>
        return result_
    }
    </#if>
</#macro>

<#--
    Expression
-->
<#macro expressionApplyBody drgElement>
        <#if transformer.isCached(modelRepository.name(drgElement))>
            if (cache_.contains("${modelRepository.name(drgElement)}")) {
                // Retrieve value from cache
                var output_:${transformer.drgElementOutputType(drgElement)} = cache_.lookup("${modelRepository.name(drgElement)}") as ${transformer.drgElementOutputType(drgElement)}

                <@endDRGElementAndReturnIndent "    " drgElement "output_" />
            } else {
                <@applySubDecisionsIndent "    " drgElement/>
                // ${transformer.evaluateElementCommentText(drgElement)}
                val output_: ${transformer.drgElementOutputType(drgElement)} = evaluate(${transformer.drgElementEvaluateArgumentList(drgElement)})
                cache_.bind("${modelRepository.name(drgElement)}", output_)

                <@endDRGElementAndReturnIndent "    " drgElement "output_" />
            }
        <#else>
            <@applySubDecisions drgElement/>
            // ${transformer.evaluateElementCommentText(drgElement)}
            val output_: ${transformer.drgElementOutputType(drgElement)} = evaluate(${transformer.drgElementEvaluateArgumentList(drgElement)})

            <@endDRGElementAndReturn drgElement "output_" />
        </#if>
</#macro>

<#macro addEvaluateExpressionMethod drgElement>
    private fun evaluate(${transformer.drgElementEvaluateSignature(drgElement)}): ${transformer.drgElementOutputType(drgElement)} {
    <#assign stm = transformer.expressionToJava(drgElement)>
    <#if transformer.isCompoundStatement(stm)>
        <#list stm.statements as child>
        ${child.expression}
        </#list>
    <#else>
        return ${stm.expression} as ${transformer.drgElementOutputType(drgElement)}
    </#if>
    }
</#macro>

<#--
    Apply direct sub-decisions
-->
<#macro applySubDecisions drgElement>
    <@applySubDecisionsIndent "" drgElement/>
</#macro>

<#macro applySubDecisionsIndent extraIndent drgElement>
    <#list modelRepository.directSubDecisions(drgElement)>
            ${extraIndent}// Apply child decisions
        <#items as subDecision>
            <#if transformer.isLazyEvaluated(subDecision)>
            ${extraIndent}val ${transformer.drgElementVariableName(subDecision)}: ${transformer.lazyEvalClassName()}<${transformer.drgElementOutputType(subDecision)}> = ${transformer.lazyEvalClassName()}({ this.${transformer.drgElementVariableName(subDecision)}.apply(${transformer.drgElementArgumentsExtraCache(subDecision)}) })
            <#else>
            ${extraIndent}val ${transformer.drgElementVariableName(subDecision)}: ${transformer.drgElementOutputType(subDecision)} = this.${transformer.drgElementVariableName(subDecision)}.apply(${transformer.drgElementArgumentsExtraCache(subDecision)})
            </#if>
        </#items>

    </#list>
</#macro>

<#--
    Events
-->
<#macro startDRGElement drgElement>
            // ${transformer.startElementCommentText(drgElement)}
            val ${transformer.drgElementVariableName(drgElement)}StartTime_ = System.currentTimeMillis()
            val ${transformer.drgElementVariableName(drgElement)}Arguments_ = ${transformer.defaultConstructor(transformer.argumentsClassName())}
            <#list transformer.drgElementArgumentNameList(drgElement)>
            <#items as arg>
            ${transformer.drgElementVariableName(drgElement)}Arguments_.put("${arg}", ${arg})
            </#items>
            </#list>
            ${transformer.eventListenerVariableName()}.startDRGElement(<@drgElementAnnotation drgElement/>, ${transformer.drgElementVariableName(drgElement)}Arguments_)
</#macro>

<#macro endDRGElement drgElement output>
    <@endDRGElementIndent "" drgElement output/>
</#macro>

<#macro endDRGElementIndent extraIndent drgElement output>
            ${extraIndent}// ${transformer.endElementCommentText(drgElement)}
            ${extraIndent}${transformer.eventListenerVariableName()}.endDRGElement(<@drgElementAnnotation drgElement/>, ${transformer.drgElementVariableName(drgElement)}Arguments_, ${output}, (System.currentTimeMillis() - ${transformer.drgElementVariableName(drgElement)}StartTime_))
</#macro>

<#macro endDRGElementAndReturn drgElement output>
            <@endDRGElementAndReturnIndent "" drgElement output/>
</#macro>

<#macro endDRGElementAndReturnIndent extraIndent drgElement output>
            <@endDRGElementIndent extraIndent drgElement output/>

            ${extraIndent}return ${output}
</#macro>

<#macro startRule drgElement rule_index>
        // Rule start
        ${transformer.eventListenerVariableName()}.startRule(<@drgElementAnnotation drgElement/>, <@ruleAnnotation/>)
</#macro>

<#macro matchRule drgElement rule_index>
            // Rule match
            ${transformer.eventListenerVariableName()}.matchRule(<@drgElementAnnotation drgElement/>, <@ruleAnnotation/>)
</#macro>

<#macro endRule drgElement rule_index output>
        // Rule end
        ${transformer.eventListenerVariableName()}.endRule(<@drgElementAnnotation drgElement/>, <@ruleAnnotation/>, ${output})
</#macro>

<#macro drgElementAnnotation drgElement>${transformer.drgElementMetadataFieldName()}</#macro>

<#macro ruleAnnotation>${transformer.drgRuleMetadataFieldName()}</#macro>

<#--
    Annotations
-->
<#macro addAnnotation drgElement rule rule_index>
            // Add annotation
            ${transformer.annotationSetVariableName()}.addAnnotation("${drgElement.name}", ${rule_index}, ${transformer.annotation(drgElement, rule)})
</#macro>
