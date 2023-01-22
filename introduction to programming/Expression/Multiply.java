package expression;

public class Multiply extends BinaryOperator {

	public Multiply(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
	}

	@Override
	String getOperator() {
		return "*";
	}

	@Override
	int getPriority() {
		return 10;
	}

	@Override
	protected boolean isAssociativeWithRight() {
		return (right instanceof Multiply);
	}

	@Override
	public int func(int x, int y) {
		return x * y;
	}
}
