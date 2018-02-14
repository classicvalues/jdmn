/**
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.runtime;

import com.gs.dmn.feel.lib.DoubleMixedJavaTimeFEELLib;
import com.gs.dmn.runtime.annotation.AnnotationTarget;
import com.gs.dmn.runtime.annotation.DRGElement;
import com.gs.dmn.runtime.annotation.Rule;

import javax.xml.datatype.Duration;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

public class DoubleMixedJavaTimeDMNBaseDecision extends DoubleMixedJavaTimeFEELLib implements DMNDecision<Double, LocalDate, OffsetTime, ZonedDateTime, Duration>, AnnotationTarget{
    @Override
    public DRGElement getDRGElementAnnotation() {
        return this.getClass().getAnnotation(DRGElement.class);
    }

    @Override
    public Rule getRuleAnnotation(int ruleIndex) {
        String methodName = String.format("rule%d", ruleIndex);
        Class<? extends DoubleMixedJavaTimeDMNBaseDecision> cls = this.getClass();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (methodName.equals(method.getName())) {
                return method.getAnnotation(Rule.class);
            }
        }
        return null;
    }
}
