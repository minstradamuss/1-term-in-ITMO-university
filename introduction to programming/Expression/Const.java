package expression;

public class Const extends PrioritizedExpression {
	final private int value;

	public Const(final int c) {
		value = c;
	}

	@Override
	public int evaluate(int x) {
		return value;
	}

	@Override
	public int evaluate(int x, int y, int z) {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().isInstance(obj)) {
			return false;
		}
		Const o = (Const) obj;
		return value == o.value;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(value) * 37;
	}

}
