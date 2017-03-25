package com.mrdai.markj;

import java.util.ArrayList;
import java.util.List;

public class MarkdownNode {
    private final List<MarkdownNode> children = new ArrayList<>();

    public void addChild(MarkdownNode child) {
        children.add(child);
    }

    public List<MarkdownNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (MarkdownNode child : getChildren())
            builder.append(child.toString());
        return builder.toString();
    }
}
