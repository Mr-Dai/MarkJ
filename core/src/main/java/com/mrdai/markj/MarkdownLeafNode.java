package com.mrdai.markj;

import java.util.Collections;
import java.util.List;

public abstract class MarkdownLeafNode extends MarkdownNode {

    @Override
    public void addChild(MarkdownNode child) {
        throw new UnsupportedOperationException("`MarkdownLeafNode` cannot have child.");
    }

    public List<MarkdownNode> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public abstract String toString();
}
