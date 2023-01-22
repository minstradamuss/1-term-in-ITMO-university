package expression;

public class Variable extends PrioritizedExpression {
	final private String variable;

	public Variable(final String var) {
		variable = var;
	}

	@Override
	public int evaluate(int x) {
		if (variable.equals("x")) {
			return x;
		}
		throw new UnknownVariable(variable);
	}

	@Override
	public int evaluate(int x, int y, int z) {
		if (variable.equals("x")) {
			return x;
		}
		if (variable.equals("y")) {
			return y;
		}
		if (variable.equals("z")) {
			return z;
		}
		throw new UnknownVariable(variable);
	}

	@Override
	public String toString() {
		return variable;
	}

	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().isInstance(obj)) {
			return false;
		}
		Variable o = (Variable) obj;
		return variable == o.variable;
	}

	@Override
	public int hashCode() {
		return variable.hashCode() * 31;
	}

}
