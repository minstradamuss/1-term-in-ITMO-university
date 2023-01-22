package expression;

public class Add extends BinaryOperator {

	public Add(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
	}

	@Override
	String getOperator() {
		return "+";
	}

	@Override
	protected boolean isAssociativeWithRight() {
		return (right instanceof Add || right instanceof Subtract);
	}

	@Override
	public int func(int x, int y) {
		return x + y;
	}
}
