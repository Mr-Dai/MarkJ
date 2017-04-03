package com.mrdai.markj;

public class EmphasisText extends MarkdownLeafNode {
    private final String surrounding;
    private final String text;

    public EmphasisText(String text) {
        this(text, true);
    }

    public EmphasisText(String text, boolean useStar) {
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("Text of an EmphasisText cannot be empty.");
        this.text = text.trim();
        this.surrounding = useStar ? "*" : "_";
    }

    @Override
    public String toString() {
        return surrounding + text + surrounding;
    }
}
