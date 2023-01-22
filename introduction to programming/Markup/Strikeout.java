package markup;

import java.util.List;

class Strikeout extends Mark implements TextElem {

    public Strikeout(List<TextElem> e) {
        super(e);
    }

    public void toMarkdownElem(StringBuilder sb) {
        toMarkdown(sb, "~");
    }

    public void toTexElem(StringBuilder sb) {
        toTex(sb, "\\textst{", "}");
    }
}