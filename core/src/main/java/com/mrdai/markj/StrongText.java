package com.mrdai.markj;

public class StrongText extends MarkdownLeafNode {
    private final String surrounding;
    private final String text;

    public StrongText(String text) {
        this(text, true);
    }

    public StrongText(String text, boolean useStar) {
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("The text of a StrongText cannot be empty.");
        this.text = text.trim().replaceAll("[\n\r]+", " ");
        this.surrounding = useStar ? "**" : "__";
    }

    @Override
    public String toString() {
        return surrounding + text + surrounding;
    }
}
