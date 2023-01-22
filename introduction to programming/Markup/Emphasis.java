package markup;

import java.util.List;

class Emphasis extends Mark implements TextElem {

    public Emphasis(List<TextElem> e) {
        super(e);
    }

    // :NOTE: зачем гонять элементы туда сюда
    public void toMarkdownElem(StringBuilder sb) {
        toMarkdown(sb, "*");
    }

    public void toTexElem(StringBuilder sb) {
        toTex(sb, "\\emph{", "}");
    }
}