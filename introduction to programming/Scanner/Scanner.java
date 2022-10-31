import java.util.*;
import java.io.*;

public class Scanner {
	private Reader input;
	private int curStr = 0;
	private int flag = 0;
	private int curStrLine = 0;
	private String first;
	private String now;
	  
	public Scanner(String a) {
		this.input = new BufferedReader(new StringReader(a));
	}
	public Scanner(StringBuffer a) {
		this.input = new BufferedReader(new StringReader(a.toString()));
	}
	public Scanner(InputStream a) {
		this.input = new BufferedReader(new InputStreamReader(a));
	}
	
	public Scanner(InputStreamReader a) {
		this.input = new BufferedReader(a);
	} 
	
	public boolean hasNextLine() throws IOException {
		return curStr != -1;
	}

	public ArrayList<Word> getWords() throws IOException {
		ArrayList<Word> arr = new ArrayList<Word>();

		int lineNum = 1;
		int wordCounter;
		int offset = 0;
		String line;

		while (this.hasNextLine()) {
			wordCounter = 0;

			line = this.nextLine();
			line = line.concat(" ");

			int start = -1;
			for (int i = 0; i < line.length(); i++) {

				if (!checkWord(line.charAt(i))) {
					if (start == -1) {
						start = i;
					}
				} else {
					if (start != -1) {
						wordCounter++;

						var word = line.substring(start, i).toLowerCase();

						arr.add(new Word(word, offset + start, lineNum, wordCounter));
						start = -1;
					}
				}
			}

			offset += line.length() - 1;
			lineNum++;
		}

		return arr;
	}

	private boolean checkWord (char c) {
		return !(Character.isLetter(c)) && Character.getType(c) != Character.DASH_PUNCTUATION && c != '\'';
	}

	private void readChars() throws IOException {
		StringBuilder str = new StringBuilder();
		curStr = input.read();
		while (curStr != -1) {
			if ((char) curStr == '\n') {
				curStrLine++;
			}
			if (checkWord((char)curStr)) {
				if (str.length() > 0) {
					break;
				}
			}
			else {
				str.append((char)curStr);
			}
			curStr = input.read();
		}
		first = now;
		now = str.toString();
	}
	  
	public boolean hasNextWord() throws IOException {
		if (flag == 0) {
			readChars();
			flag = 1;
		}
		int nowLen = now.length();
		return nowLen != 0;
	}
	  
	public String nextWord() throws IOException {
		readChars();
		return first;
	}
	  
	private boolean checkNumber(char c) {
		return !Character.isDigit(c) && c != '-';
	}

	private void readDigits() throws IOException {
		StringBuilder str = new StringBuilder();
		curStr = input.read();
		while (curStr != -1) {
			if ((char) curStr == '\n') {
				curStrLine++;
			}
			if (checkNumber((char) curStr)) {
				if (str.length() > 0) {
				break;
				}
			} else {
				if ((char) curStr == '-') {
					if (str.length() == 0) {
						str.append((char) curStr);
					} else {
						break;
					}
				} else {
					str.append((char) curStr);
				}
			}
			curStr = input.read();
		}
		first = now;
		now = str.toString();
	}

	public boolean hasNextInt() throws IOException {
		if (flag == 0) {
			readDigits();
			flag = 1;
		}
		int nowLen = now.length();
		return nowLen != 0;
	}
	  
	public int nextInt() throws IOException {
		readDigits();
		return Integer.parseInt(first);
	}
	  
	public String nextLine() throws IOException {
		StringBuilder str = new StringBuilder();
		curStr = input.read();
		while (curStr != -1) {
			if ((char) curStr == '\n') {
				break;
			} else {
				str.append((char) curStr);
			}
			curStr = input.read();
		}
		first = now;
		now = str.toString();
		return now;
	}
	  
	public int curStrLine() {
		return curStrLine;
	}

	public void close() {
		try {
			input.close();
		} catch (IOException e) {
			System.out.println ("Look, this is IOException" + e.getMessage());
		}  
	}
}