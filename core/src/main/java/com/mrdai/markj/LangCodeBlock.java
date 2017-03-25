package com.mrdai.markj;

public class LangCodeBlock extends MarkdownLeafNode {
    private final String lang;
    private final String text;

    public LangCodeBlock(String lang, String text) {
        this.lang = lang;
        this.text = text;
    }

    @Override
    public String toString() {
        return "```" + lang + "\n" + text + "\n```\n\n";
    }
}
