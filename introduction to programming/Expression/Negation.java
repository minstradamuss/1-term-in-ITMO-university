package expression;

public class Negation extends UnaryOperator {
	public Negation(PrioritizedExpression arg) {
		super(arg);
	}

	@Override
	public int func(int arg) {
		return -arg;
	}

	@Override
	String getOperator() {
		return "-";
	}
}
