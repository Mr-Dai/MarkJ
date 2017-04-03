package com.mrdai.markj;

public class Header extends MarkdownLeafNode {
    private final int level;
    private final String text;

    public Header(int level, String text) {
        if (level < 1 || level > 5)
            throw new IllegalArgumentException("Level of header can only be within [1, 5].");
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("Text of header cannot be empty.");
        this.level = level;
        this.text = text.trim();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++)
            builder.append('#');
        builder.append(' ').append(text).append("\n\n");
        return builder.toString();
    }
}
