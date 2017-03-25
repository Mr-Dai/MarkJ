package com.mrdai.markj;

public class EmphasisText extends MarkdownLeafNode {
    private final String surrounding;
    private final String text;

    public EmphasisText(String text) {
        this(text, false);
    }

    public EmphasisText(String text, boolean useStar) {
        this.text = text.trim();
        this.surrounding = useStar ? "*" : "_";
    }

    @Override
    public String toString() {
        return surrounding + text + surrounding;
    }
}
