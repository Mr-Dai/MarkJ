package com.mrdai.markj;

public class Image extends MarkdownLeafNode {
    private final String link;
    private final String altText;
    private final String optionalTitle;

    public Image(String link) {
        this(link, "", "");
    }

    public Image(String link, String altText, String optionalTitle) {
        if (link == null || link.trim().isEmpty())
            throw new IllegalArgumentException("The link of an image cannot be empty.");
        this.link = link;
        this.altText = altText == null ? "" : altText;
        this.optionalTitle = optionalTitle == null ? "" : optionalTitle;
    }

    @Override
    public String toString() {
        return "![" + altText + "](" + link + (optionalTitle.isEmpty() ? ")\n\n" : " \"" + optionalTitle + "\")\n\n");
    }
}
