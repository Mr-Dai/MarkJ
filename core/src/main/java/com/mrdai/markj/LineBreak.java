package com.mrdai.markj;

public class LineBreak extends MarkdownLeafNode {
    private static final LineBreak INSTANCE = new LineBreak();

    public static LineBreak get() {
        return INSTANCE;
    }

    private LineBreak() {}

    @Override
    public String toString() {
        return "    \n";
    }
}
