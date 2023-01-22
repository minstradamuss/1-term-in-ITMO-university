package expression;

public class OperatorClear extends BinaryOperator {

	public OperatorClear(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
	}

	@Override
	String getOperator() {
		return "clear";
	}

	@Override
	public int func(int x, int y) {
		return x & ~(1<<y);
	}

	@Override
	int getPriority() {
		return -100;
	}

}
