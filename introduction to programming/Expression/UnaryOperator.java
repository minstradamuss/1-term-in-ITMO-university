package expression;

abstract public class UnaryOperator extends PrioritizedExpression {
	protected PrioritizedExpression arg;

	public UnaryOperator(PrioritizedExpression arg) {
		this.arg = arg;
	}

	public abstract int func(int arg);

	@Override
	public int evaluate(int x) {
		return this.func(arg.evaluate(x));
	}

	@Override
	public int evaluate(int x, int y, int z) {
		return this.func(arg.evaluate(x, y, z));
	}

	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().isInstance(obj)) {
			return false;
		}
		UnaryOperator o = (UnaryOperator) obj;
		return arg.equals(o.arg);
	}

	@Override
	public int hashCode() {
		return arg.hashCode() * 53 + getClass().hashCode() * 59;
	}

	abstract String getOperator();

	@Override
	public String toString() {
		return String.format("%s(%s)", getOperator(), arg.toString());
	}

	@Override
	public String toMiniString() {
		String arg = this.arg.toMiniString();
		if (this.arg.getPriority() < this.getPriority()) {
			return String.format("%s(%s)", getOperator(), arg);
		}
		return String.format("%s %s", getOperator(), arg);
	}
}
