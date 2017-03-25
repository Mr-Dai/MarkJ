package com.mrdai.markj;

public class UnorderedList extends MarkdownNode {
    private final char icon;

    public UnorderedList(char icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (MarkdownNode child : getChildren()) {
            String[] childStr = child.toString().split("\n+");
            builder.append(icon).append(' ').append(childStr[0].trim()).append('\n');
            for (int i = 1; i < childStr.length; i++)
                builder.append("  ").append(childStr[i].trim()).append('\n');
        }
        builder.append('\n');
        return builder.toString();
    }
}
