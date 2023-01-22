package markup;

import java.util.List;

class Strong extends Mark implements TextElem {

    public Strong(List<TextElem> e) {
        super(e);
    }

    public void toMarkdownElem(StringBuilder sb) {
        toMarkdown(sb, "__");
    }

    public void toTexElem(StringBuilder sb) {
        toTex(sb, "\\textbf{", "}");
    }
}