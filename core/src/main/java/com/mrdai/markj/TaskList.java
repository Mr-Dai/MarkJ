package com.mrdai.markj;

public class TaskList extends UnorderedList {

    public TaskList() {
        super('-');
    }

    @Override
    public void addChild(MarkdownNode child) {
        if (!(child instanceof Entry))
            throw new IllegalArgumentException("Child of a TaskList can only be TaskList.Entry.");
        super.addChild(child);
    }

    public static class Entry extends MarkdownLeafNode {
        private final String text;
        private final boolean finished;

        public Entry(String text) {
            this(text, false);
        }

        public Entry(String text, boolean finished) {
            this.text = text.trim();
            this.finished = finished;
        }

        @Override
        public String toString() {
            return "[" + (finished ? "x" : " ") + "] " + text;
        }
    }
}
