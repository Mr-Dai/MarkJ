package com.mrdai.markj;

public class InlineCode extends MarkdownLeafNode {
    private final String code;

    public InlineCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "`" + code + "`";
    }
}
