package expression.exceptions;

import expression.PrioritizedExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {

	public CheckedSubtract(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int func(int x, int y) {
		// TODO Auto-generated method stub
		int result = super.func(x, y);
		if (x >= 0 && y <= 0 && result < 0 || x <= 0 && y >= 0 && result > 0) {
			throw new OverflowError();
		}
		return result;
	}
}
