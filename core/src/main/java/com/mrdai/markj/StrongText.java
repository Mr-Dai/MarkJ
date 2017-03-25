package com.mrdai.markj;

public class StrongText extends MarkdownLeafNode {
    private final String surrounding;
    private final String text;

    public StrongText(String text) {
        this(text, false);
    }

    public StrongText(String text, boolean useStar) {
        this.text = text.trim();
        this.surrounding = useStar ? "**" : "__";
    }

    @Override
    public String toString() {
        return surrounding + text + surrounding;
    }
}
