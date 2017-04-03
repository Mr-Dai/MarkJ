package com.mrdai.markj;

public class StrikeThroughText extends MarkdownLeafNode {
    private final String text;

    public StrikeThroughText(String text) {
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("The text of a StrikeThroughText cannot be empty.");
        this.text = text.trim().replaceAll("[\n\r]+", " ");
    }

    @Override
    public String toString() {
        return "~~" + text + "~~";
    }
}
