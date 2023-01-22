package expression;

public abstract class BinaryOperator extends PrioritizedExpression {
	protected final PrioritizedExpression left, right;

	/**
	 * Returns string representation of this operator.
	 * 
	 * @return The string representation of this operator
	 */
	abstract String getOperator();

	/**
	 * Returns whether we can drop parentheses around the right subexpression of the
	 * same priority.
	 * 
	 * The right subexpression must be guaranteed to have the same priority as this.
	 * 
	 * @return True if we can drop parentheses around the right subexpression
	 */
	protected boolean isAssociativeWithRight() {
		return false;
	}

	/**
	 * Return operation result
	 * 
	 * @param x First argument
	 * @param y Second argument
	 * @return Operation result
	 */
	public abstract int func(int x, int y);

	@Override
	public int evaluate(int x) {
		return func(left.evaluate(x), right.evaluate(x));
	}

	@Override
	public int evaluate(int x, int y, int z) {
		return func(left.evaluate(x, y, z), right.evaluate(x, y, z));
	}

	@Override
	int getPriority() {
		return 0;
	}

	public BinaryOperator(final PrioritizedExpression left, final PrioritizedExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().isInstance(obj)) {
			return false;
		}
		BinaryOperator o = (BinaryOperator) obj;
		return left.equals(o.left) && right.equals(o.right);
	}

	@Override
	public String toString() {
		return String.format("(%s %s %s)", left, getOperator(), right);
	}

	private boolean needParenthesesLeft() {
		return this.getPriority() > left.getPriority();
	}

	private boolean needParenthesesRight() {
		return (this.getPriority() > right.getPriority())
				|| (this.getPriority() == right.getPriority() && !this.isAssociativeWithRight());
	}

	@Override
	public String toMiniString() {
		String left = this.left.toMiniString();
		String right = this.right.toMiniString();
		if (needParenthesesLeft()) {
			left = String.format("(%s)", left);
		}
		if (needParenthesesRight()) {
			right = String.format("(%s)", right);
		}
		return String.format("%s %s %s", left, getOperator(), right);
	}

	@Override
	public int hashCode() {
		return left.hashCode() * 41 + right.hashCode() * 43 + getClass().hashCode() * 47;
	}
}
