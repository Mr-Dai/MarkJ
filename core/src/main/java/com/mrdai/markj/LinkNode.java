package com.mrdai.markj;

public class LinkNode extends MarkdownLeafNode {
    private final String text;
    private final String href;

    public LinkNode(String text, String href) {
        this.text = text;
        this.href = href;
    }

    @Override
    public String toString() {
        return "[" + text + "](" + href + ")";
    }
}
