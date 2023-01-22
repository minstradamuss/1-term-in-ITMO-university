package expression;

public class Main {

	public static void main(String[] args) {
		Variable x = new Variable("x");
		Expression expr = new Add(new Subtract(new Multiply(x, x), new Multiply(new Const(2), x)), new Const(1));
		System.out.println(expr.toMiniString());
		int result = expr.evaluate(10);
		System.out.println(result);
		if (result != 81)
			System.out.println("oops...");
	}
}
