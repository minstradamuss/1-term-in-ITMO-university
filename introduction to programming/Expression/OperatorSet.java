package expression;

public class OperatorSet extends BinaryOperator {

	public OperatorSet(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
	}

	@Override
	String getOperator() {
		return "set";
	}

	@Override
	public int func(int x, int y) {
		return x | (1 << y);
	}
	
	@Override
	int getPriority() {
		return -100;
	}

}
