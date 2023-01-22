package expression.exceptions;

import expression.Multiply;
import expression.PrioritizedExpression;

public class CheckedMultiply extends Multiply {

	public CheckedMultiply(PrioritizedExpression left, PrioritizedExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int func(int x, int y) {
		int result = super.func(x, y);
		checkSigns(x, y, result);
		checkOverflow(x, y, result);
		checkOverflow(x, y, result);

		return result;
	}

	@Override
	public int evaluate(int x, int y, int z) {
		int left = this.left.evaluate(x, y, z);
		int right = this.right.evaluate(x, y, z);
		int result = this.func(left, right);
		// System.out.println(String.format("%d*%d=%d", left, right, result));

		checkSigns(left, right, result);
		checkOverflow(left, right, result);
		checkOverflow(right, left, result);

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
		if (result > 0 && expectedSign > 0)
			return;
		if (result < 0 && expectedSign < 0)
			return;
		throw new OverflowError();
	}

	private static void checkOverflow(int left, int right, int result) {
		if (right == 0)
			if (result != 0)
				throw new OverflowError();
			else
				return;

		int div = result / right;
		int mod = result % right;
		if (left != div || mod != 0)
			throw new OverflowError();
	}

}
