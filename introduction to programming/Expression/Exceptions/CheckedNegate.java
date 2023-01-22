package expression.exceptions;

import expression.Negation;
import expression.PrioritizedExpression;

public class CheckedNegate extends Negation {

	public CheckedNegate(PrioritizedExpression arg) {
		super(arg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int func(int arg) {
		if (arg == Integer.MIN_VALUE)
			throw new OverflowError();
		return super.func(arg);
	}
}
