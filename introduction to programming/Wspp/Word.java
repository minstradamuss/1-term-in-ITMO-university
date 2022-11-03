public class Word {
    private String _text;
    private int _absPos;
    private int _lineNum;
    private int _wordNum;

    Word(String text, int absPos, int lineNum, int wordNum) {
        _text = text;
        _absPos = absPos;
        _lineNum = lineNum;
        _wordNum = wordNum;
    }
    public String getText() {
        return _text;
    }
    public void setText(String text) {
        _text = text;
    }

    public int getPos() {
        return _absPos;
    }
    public void setPos(int pos) {
        _absPos = pos;
    }

    public int getLine() {
        return _lineNum;
    }
    public void setLine(int line) {
        _lineNum = line;
    }
    public int getWord() {
        return _wordNum;
    }
}
