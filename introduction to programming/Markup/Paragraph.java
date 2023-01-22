package markup;

import java.util.List;

public class Paragraph {

    public List<TextElem> elems;

    public Paragraph(List<TextElem> e) {
        this.elems = e;
    }

    public void toMarkdown(StringBuilder sb) {
        for (int k = 0; k < elems.size(); k++) {
            elems.get(k).toMarkdownElem(sb);
        }
    }

    public void toTex(StringBuilder sb) {
        for (int k = 0; k < elems.size(); k++) {
            elems.get(k).toTexElem(sb);
        }
    }
}