package expression.exceptions;

import expression.Const;
import expression.OperatorClear;
import expression.OperatorSet;
import expression.PrioritizedExpression;
import expression.TripleExpression;
import expression.Variable;

public class ExpressionParser implements TripleParser {

	int position;
	String expression;

	public ExpressionParser() {
	}

	@Override
	public TripleExpression parse(String expression) throws ParserError {
		this.expression = expression;
		this.position = 0;
		//System.out.println(expression);

		PrioritizedExpression result = this.parseExpression();
		if (nextChar() != '\0') {
			throw new ExtraCharactersError();
		}
		return result;
	}
	
	private char prevChar() {
		if(position == 0 || position > expression.length()) {
			return '\0';
		}
		return expression.charAt(position-1);
	}

	private char nextChar() {
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

	private PrioritizedExpression parseParentheses() throws ParserError {
		char ch = nextChar();
		if (ch != '(')
			throw new IllegalStateException();
		skipChar();

		PrioritizedExpression result = parseExpression();

		ch = nextChar();
		if (ch != ')')
			throw new ExpectedClosingParenthesisError();
		skipChar();
		return result;
	}

	private PrioritizedExpression parseExpression() throws ParserError {
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

	private boolean skipKeyword(String string) throws ParserError {
		while (Character.isWhitespace(nextChar())) {
			position += 1;
		}
		if(Character.isLetterOrDigit(prevChar()))
			return false;
		
		if (expression.substring(position).startsWith(string)) {
			position += string.length();
			return true;
		}
		
		return false;
	}

	private PrioritizedExpression parseNegation() throws ParserError {
		char ch = nextChar();
		if (ch != '-')
			throw new IllegalStateException();
		skipChar();

		ch = nextChar(false);
		if ('1' <= ch && ch <= '9') {
			int integer = parseInteger();
			return new Const(-integer);
		}

		PrioritizedExpression result = parseFactor();
		return new CheckedNegate(result);
	}

	private PrioritizedExpression parseSum() throws ParserError {
		PrioritizedExpression result = parseTerm();
		while (true) {
			char ch = nextChar();
			if (ch != '+' && ch != '-')
				break;
			skipChar();
			PrioritizedExpression second = parseTerm();
			if (ch == '+') {
				result = new CheckedAdd(result, second);
			} else {
				result = new CheckedSubtract(result, second);
			}
		}
		return result;
	}

	private PrioritizedExpression parseTerm() throws ParserError {
		PrioritizedExpression result = parseFactor();
		while (true) {
			char ch = nextChar();
			if (ch != '*' && ch != '/')
				break;
			skipChar();
			PrioritizedExpression second = parseFactor();
			if (ch == '*') {
				result = new CheckedMultiply(result, second);
			} else {
				result = new CheckedDivide(result, second);
			}
		}
		return result;
	}

	private PrioritizedExpression parseFactor() throws ParserError {
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
			int integer = parseInteger();
			if (integer < 0) {
				throw new ConstOutOfRangeError();
			}
			return new Const(integer);
		}
		throw new ValueMissingError();
	}

	private int parseInteger() throws ParserError {
		int integer = 0;
		char ch = nextChar();
		if (!('0' <= ch && ch <= '9'))
			throw new IllegalStateException();
		while ('0' <= ch && ch <= '9') {
			integer = integer * 10 + (ch - '0');
			if (integer < 0 && integer != Integer.MIN_VALUE) {
				throw new ConstOutOfRangeError();
			}
			skipChar();
			ch = nextChar(false);
		}
		return integer;
	}

}
