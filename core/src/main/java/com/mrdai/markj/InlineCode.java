package com.mrdai.markj;

public class InlineCode extends MarkdownLeafNode {
    private final String code;

    public InlineCode(String code) {
        if (code == null || code.trim().isEmpty())
            throw new IllegalArgumentException("Inner text of an InlineCode cannot be empty.");
        this.code = code.trim().replace("`", "\\`").replaceAll("[\r\n]+", " ");
    }

    @Override
    public String toString() {
        return "`" + code + "`";
    }
}
