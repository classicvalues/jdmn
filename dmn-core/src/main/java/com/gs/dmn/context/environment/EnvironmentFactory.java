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
package com.gs.dmn.context.environment;

import com.gs.dmn.context.DMNContext;
import com.gs.dmn.el.analysis.semantics.type.Type;
import com.gs.dmn.el.analysis.syntax.ast.expression.Expression;

public interface  EnvironmentFactory {
    default Environment emptyEnvironment() {
        return new Environment();
    }

    default Environment makeEnvironment(Expression inputExpression) {
        return new Environment(inputExpression);
    }

    default Declaration makeVariableDeclaration(String name, Type type) {
        return new VariableDeclaration(name, type);
    }

    DMNContext getBuiltInContext();
}
