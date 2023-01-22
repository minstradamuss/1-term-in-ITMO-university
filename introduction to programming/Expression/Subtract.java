package expression;

public class Subtract extends BinaryOperator {

	public Subtract(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
	}

	@Override
	String getOperator() {
		return "-";
	}

	@Override
	public int func(int x, int y) {
		return x - y;
	}
}
