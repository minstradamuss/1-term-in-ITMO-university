package markup;

import java.util.List;

abstract class Mark {
    protected List<TextElem> elems;

    protected Mark(List<TextElem> elements) {
        elems = elements;
    }

    protected void toMarkdown(StringBuilder sb, String mark) {
        sb.append(mark);
        for (TextElem elem : elems) {
            elem.toMarkdownElem(sb);
        }
        sb.append(mark);
    }

    protected void toTex(StringBuilder sb, String left, String right) {
        sb.append(left);
        for (TextElem elem : elems) {
            elem.toTexElem(sb);
        }
        sb.append(right);
    }
}
