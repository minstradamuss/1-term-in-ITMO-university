package expression;

public abstract class PrioritizedExpression implements Expression, TripleExpression {
	/**
	 * Returns computational priority of this subexpression in string representation
	 * @return Expression's root item priority
	 */
	int getPriority() {
		return 1000;
	}
}
