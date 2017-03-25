package com.mrdai.markj;

public class CodeBlock extends MarkdownLeafNode {
    private final String lang;
    private final String code;

    public CodeBlock(String code) {
        this("", code);
    }

    public CodeBlock(String lang, String code) {
        this.lang = lang;
        this.code = code;
    }

    @Override
    public String toString() {
        return "```" + lang + "\n" + code + "\n```\n\n";
    }
}
