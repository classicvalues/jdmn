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
package com.gs.dmn.maven;

import com.gs.dmn.feel.analysis.semantics.environment.DefaultDMNEnvironmentFactory;
import com.gs.dmn.runtime.DefaultDMNBaseDecision;

import java.util.LinkedHashMap;
import java.util.Map;

public class AbstractMojoTest {
    protected Map<String, String> makeParams() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("environmentFactoryClass", DefaultDMNEnvironmentFactory.class.getName());
        map.put("decisionBaseClass", DefaultDMNBaseDecision.class.getName());

        map.put("dmnVersion", "1.1");
        map.put("modelVersion", "2.0");
        map.put("platformVersion", "1.0");
        return map;
    }
}
