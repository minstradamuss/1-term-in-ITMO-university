package expression.exceptions;

import expression.Add;
import expression.PrioritizedExpression;

public class CheckedAdd extends Add {

	public CheckedAdd(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
	}

	@Override
	public int func(int x, int y) {
		// TODO Auto-generated method stub
		int result = super.func(x, y);

		if (x > 0 && y > 0 && result <= 0 || x < 0 && y < 0 && result >= 0) {
			throw new OverflowError();
		}
		return result;
	}

}
