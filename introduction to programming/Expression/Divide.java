package expression;

public class Divide extends BinaryOperator {

	public Divide(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
	}

	@Override
	String getOperator() {
		return "/";
	}

	@Override
	int getPriority() {
		return 10;
	}

	@Override
	public int func(int x, int y) {
		return x / y;
	}
}
