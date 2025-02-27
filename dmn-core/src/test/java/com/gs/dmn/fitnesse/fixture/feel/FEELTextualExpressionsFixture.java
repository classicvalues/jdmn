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

import com.gs.dmn.context.DMNContext;
import com.gs.dmn.el.analysis.semantics.type.Type;
import com.gs.dmn.el.analysis.syntax.ast.expression.Expression;

public class FEELTextualExpressionsFixture extends FEELFixture {
    private String expression;

    public FEELTextualExpressionsFixture() {
        super();
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Object output() {
        Expression<Type, DMNContext> result = this.feelInterpreter.parseTextualExpressions(this.expression);
        return result.toString();
    }
}
