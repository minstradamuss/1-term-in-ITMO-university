package markup;

interface TextElem {
    public void toMarkdownElem(StringBuilder sb);

    public void toTexElem(StringBuilder sb);
}