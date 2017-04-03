package com.mrdai.markj;

// TODO Add auto escape
public class PlainText extends MarkdownLeafNode {
    private final String text;

    public PlainText(String text) {
        if (text == null || text.isEmpty())
            throw new IllegalArgumentException("The text of a PlainText cannot be empty.");
        this.text = text.replace("`", "\\`");
    }

    @Override
    public String toString() {
        return text;
    }
}
