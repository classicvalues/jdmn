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
package com.gs.dmn.fitnesse.fixture.feel;

import com.gs.dmn.feel.analysis.semantics.type.Type;
import com.gs.dmn.feel.analysis.syntax.ast.test.UnaryTests;
import com.gs.dmn.runtime.DMNContext;

public class FEELUnaryTestsFixture extends FEELFixture {
    private String inputEntry;

    public FEELUnaryTestsFixture() {
        super();
    }

    public void setInputEntry(String inputEntry) {
        this.inputEntry = inputEntry;
    }

    public Object output() {
        UnaryTests<Type, DMNContext> unaryTests = this.feelInterpreter.parseUnaryTests(this.inputEntry);
        return unaryTests.toString();
    }
}
