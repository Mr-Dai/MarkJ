package com.mrdai.markj;

public class HorizontalRule extends MarkdownLeafNode {
    private static final HorizontalRule INSTANCE = new HorizontalRule();

    public static HorizontalRule get() {
        return INSTANCE;
    }

    private HorizontalRule() {}

    @Override
    public String toString() {
        return "---\n\n";
    }
}
