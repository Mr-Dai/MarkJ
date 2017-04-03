package com.mrdai.markj;

public class LinkNode extends MarkdownLeafNode {
    private final String text;
    private final String href;

    public LinkNode(String text, String href) {
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("The text of a hyperlink cannot be empty.");
        if (href == null || href.trim().isEmpty())
            throw new IllegalArgumentException("The href of a hyperlink cannot be empty.");
        this.text = text;
        this.href = href;
    }

    @Override
    public String toString() {
        return "[" + text + "](" + href + ")";
    }
}
