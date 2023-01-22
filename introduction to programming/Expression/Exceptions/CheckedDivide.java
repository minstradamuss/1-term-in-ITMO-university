package expression.exceptions;

import expression.Divide;
import expression.PrioritizedExpression;

public class CheckedDivide extends Divide {

	public CheckedDivide(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int func(int x, int y) {
		if (y == 0) {
			throw new ZeroDivisionError();
		}
		int result = super.func(x, y);
		checkSigns(x, y, result);
		return result;
	}

	private static void checkSigns(int left, int right, int result) {
		int expectedSign = 0;
		if (left > 0 && right > 0 || left < 0 && right < 0) {
			expectedSign = 1;
		} else {
			expectedSign = -1;
		}
		if (left == 0 || right == 0) {
			expectedSign = 0;
		}
		if (result == 0 && expectedSign == 0)
			return;
		if (result >= 0 && expectedSign > 0)
			return;
		if (result <= 0 && expectedSign < 0)
			return;
		throw new OverflowError();
	}

}
