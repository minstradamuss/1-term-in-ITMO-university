package expression;

public class UnknownVariable extends IllegalArgumentException {
	public final String variable;

	public UnknownVariable(final String var) {
		super("Unkown variable: " + var);
		variable = var;
	}
}
