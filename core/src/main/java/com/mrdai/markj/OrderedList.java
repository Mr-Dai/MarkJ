package com.mrdai.markj;

public class OrderedList extends MarkdownNode {
    private final int base;

    public OrderedList(int base) {
        this.base = base;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int current = base;
        for (MarkdownNode child : getChildren()) {
            String[] childStr = child.toString().split("\n+");
            builder.append(current).append(". ").append(childStr[0].trim()).append('\n');
            for (int i = 1; i < childStr.length; i++)
                builder.append("   ").append(childStr[i].trim()).append('\n');
            current++;
        }
        builder.append('\n');
        return builder.toString();
    }
}
