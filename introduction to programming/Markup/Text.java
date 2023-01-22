package markup;

class Text implements TextElem {
    private final String data;

    public Text(String s) {
        data = s;
    }

    public void toMarkdownElem(StringBuilder sb) {
        sb.append(data);
    }

    public void toTexElem(StringBuilder sb) {
        sb.append(data);
    }
}
