package com.altenlab.algo.misc;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionParserTest {
    class ExpressionData {
        private final int expected;
        private final String expression;

        public ExpressionData(final String expression, final int expected) {
            this.expression = expression;
            this.expected = expected;
        }

        public int getExpected() {
            return expected;
        }

        public String getExpression() {
            return expression;
        }
    }
    @Test
    void calculate() {
        final ArrayList<ExpressionData> data = new ArrayList<>();
        data.add(new ExpressionData("0-2-4-3", -9));
        data.add(new ExpressionData("10-2+6", 14));
        data.add(new ExpressionData("10-2+6+0", 14));


        final ExpressionParser parser = new ExpressionParser();
        for( final ExpressionData equation : data ) {
            assertEquals(equation.getExpected(), parser.calculate(equation.getExpression()));
        }
    }

    @Test
    void calculateWithParentheses() {
        final ArrayList<ExpressionData> data = new ArrayList<>();
        data.add(new ExpressionData("22+(2-4)-12", 8));
        data.add(new ExpressionData("22+(2-4)", 20));
        data.add(new ExpressionData("5+16-((9-6)-(4-2))", 20));


        final ExpressionParser parser = new ExpressionParser();
        for( final ExpressionData equation : data ) {
            assertEquals(equation.getExpected(), parser.calculateWithParentheses(equation.getExpression()));
        }
    }

}