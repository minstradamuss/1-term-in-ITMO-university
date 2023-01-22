package expression.exceptions;

import expression.TripleExpression;

public class Main {

	public static void main(String[] args) {
		try {
			TripleExpression expr = new ExpressionParser().parse("x/y");
			int result = expr.evaluate(15, 2, 3);
			System.out.println(expr.toMiniString() + " = " + result);
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

}
