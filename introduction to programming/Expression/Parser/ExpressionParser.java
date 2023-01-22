package expression.parser;

import expression.Add;
import expression.Const;
import expression.Divide;
import expression.Multiply;
import expression.Negation;
import expression.OperatorClear;
import expression.OperatorSet;
import expression.PrioritizedExpression;
import expression.Subtract;
import expression.TripleExpression;
import expression.Variable;

public class ExpressionParser implements TripleParser {

	int position;
	String expression;

	public ExpressionParser() {
	}

	@Override
	public TripleExpression parse(String expression) {
		this.expression = expression;
		this.position = 0;

		PrioritizedExpression result = this.parseExpression();
		if (nextChar() != '\0') {
			throw new IllegalArgumentException("extra characters");
		}
		return result;
	}

	private Character nextChar() {
		return nextChar(true);
	}

	private char nextChar(boolean skipSpaces) {
		while (true) {
			if (position >= expression.length())
				return '\0';
			char ch = expression.charAt(position);
			if (skipSpaces && Character.isWhitespace(ch)) {
				skipChar();
				continue;
			}
			return Character.valueOf(ch);
		}
	}

	private void skipChar() {
		position += 1;
	}

	private PrioritizedExpression parseParentheses() {
		char ch = nextChar();
		if (ch != '(')
			throw new IllegalStateException();
		skipChar();

		PrioritizedExpression result = parseExpression();

		ch = nextChar();
		if (ch != ')')
			throw new IllegalStateException();
		skipChar();
		return result;
	}

	private PrioritizedExpression parseExpression() {
		PrioritizedExpression result = parseSum();
		while (true) {
			boolean set = skipKeyword("set");
			boolean clear = false;
			if (!set) {
				clear = skipKeyword("clear");
			}
			if (!set && !clear)
				break;
			PrioritizedExpression second = parseSum();
			if (set) {
				result = new OperatorSet(result, second);
			} else {
				result = new OperatorClear(result, second);
			}
		}
		return result;
	}

	private boolean skipKeyword(String string) {
		while (expression.length() > position && Character.isWhitespace(expression.charAt(position))) {
			position += 1;
		}
		if (expression.substring(position).startsWith(string)) {
			position += string.length();
			return true;
		}
		return false;
	}

	private PrioritizedExpression parseNegation() {
		char ch = nextChar();
		if (ch != '-')
			throw new IllegalStateException();
		skipChar();

		ch = expression.charAt(position);
		if ('1' <= ch && ch <= '9') {
			int integer = parseInteger();
			return new Const(-integer);
		}

		PrioritizedExpression result = parseFactor();
		return new Negation(result);
	}

	private PrioritizedExpression parseSum() {
		PrioritizedExpression result = parseTerm();
		while (true) {
			char ch = nextChar();
			if (ch != '+' && ch != '-')
				break;
			skipChar();
			PrioritizedExpression second = parseTerm();
			if (ch == '+') {
				result = new Add(result, second);
			} else {
				result = new Subtract(result, second);
			}
		}
		return result;
	}

	private PrioritizedExpression parseTerm() {
		PrioritizedExpression result = parseFactor();
		while (true) {
			char ch = nextChar();
			if (ch != '*' && ch != '/')
				break;
			skipChar();
			PrioritizedExpression second = parseFactor();
			if (ch == '*') {
				result = new Multiply(result, second);
			} else {
				result = new Divide(result, second);
			}
		}
		return result;
	}

	private PrioritizedExpression parseFactor() {
		char ch = nextChar();
		if (ch == '(') {
			return parseParentheses();
		}
		if (ch == '-') {
			return parseNegation();
		}
		if ('x' <= ch && ch <= 'z') {
			skipChar();
			return new Variable(String.valueOf(ch));
		}
		if ('0' <= ch && ch <= '9') {
			return new Const(parseInteger());
		}
		throw new IllegalStateException();
	}

	private int parseInteger() {
		int integer = 0;
		char ch = nextChar();
		if (!('0' <= ch && ch <= '9'))
			throw new IllegalStateException();
		while ('0' <= ch && ch <= '9') {
			integer = integer * 10 + (ch - '0');
			skipChar();
			ch = nextChar(false);
		}
		return integer;
	}

}
