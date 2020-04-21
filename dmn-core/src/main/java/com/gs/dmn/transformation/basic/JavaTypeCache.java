/*
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
package com.gs.dmn.transformation.basic;

import com.gs.dmn.feel.analysis.semantics.type.Type;
import org.omg.spec.dmn._20180521.model.TDecision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class JavaTypeCache {
    protected static final Logger LOGGER = LoggerFactory.getLogger(JavaTypeCache.class);

    private Map<TDecision, String> javaTypeOfElement = new LinkedHashMap<>();
    private Map<Type, String> javaTypeOfType = new LinkedHashMap<>();

    public String get(TDecision decision) {
        return this.javaTypeOfElement.get(decision);
    }

    public void put(TDecision decision, String type) {
        this.javaTypeOfElement.put(decision, type);
    }

    public String get(Type type) {
        return this.javaTypeOfType.get(type);
    }

    public void put(Type type, String javaType) {
        this.javaTypeOfType.put(type, javaType);
    }
}
