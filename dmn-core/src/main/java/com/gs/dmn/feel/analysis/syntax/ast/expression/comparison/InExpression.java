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
package com.gs.dmn.feel.analysis.syntax.ast.expression.comparison;

import com.gs.dmn.feel.analysis.syntax.ast.Visitor;
import com.gs.dmn.feel.analysis.syntax.ast.expression.Expression;
import com.gs.dmn.feel.analysis.syntax.ast.test.PositiveUnaryTest;
import com.gs.dmn.feel.analysis.syntax.ast.test.PositiveUnaryTests;
import com.gs.dmn.runtime.DMNContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InExpression extends Comparison {
    private final Expression value;
    private final List<PositiveUnaryTest> tests = new ArrayList<>();

    public InExpression(Expression value, PositiveUnaryTest test) {
        this.value = value;
        if (test != null) {
            this.tests.add(test);
        }
    }

    public InExpression(Expression value, PositiveUnaryTests tests) {
        this.value = value;
        if (tests != null) {
            this.tests.addAll(tests.getPositiveUnaryTests());
        }
    }

    public Expression getValue() {
        return this.value;
    }

    public List<PositiveUnaryTest> getTests() {
        return this.tests;
    }

    @Override
    public Object accept(Visitor visitor, DMNContext context) {
        return visitor.visit(this, context);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InExpression that = (InExpression) o;
        return Objects.equals(value, that.value) && Objects.equals(tests, that.tests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, tests);
    }

    @Override
    public String toString() {
        String right = this.tests.stream().map(Object::toString).collect(Collectors.joining(", "));
        return String.format("%s(%s, %s)", getClass().getSimpleName(), this.value.toString(), right);
    }
}
