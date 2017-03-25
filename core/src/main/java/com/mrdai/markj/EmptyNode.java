package com.mrdai.markj;

public class EmptyNode extends MarkdownLeafNode {
    private static final EmptyNode INSTANCE = new EmptyNode();

    public static EmptyNode get() {
        return INSTANCE;
    }

    private EmptyNode() {}

    @Override
    public String toString() {
        return "";
    }
}
