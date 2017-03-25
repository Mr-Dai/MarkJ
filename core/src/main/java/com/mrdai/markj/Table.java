package com.mrdai.markj;

public class Table extends MarkdownNode {
    private final int columnNum;
    private final String[] headers;
    private final Alignment[] alignments;

    public Table(int columnNum, String... headers) {
        this.columnNum = columnNum;
        this.headers = new String[columnNum];
        int i;
        for (i = 0; i < headers.length && i < columnNum; i++)
            this.headers[i] = headers[i].replaceAll("[\n\r]", "").trim();
        for (; i < columnNum; i++)
            this.headers[i] = "";
        alignments = new Alignment[columnNum];
        for (i = 0; i < columnNum; i++)
            alignments[i] = Alignment.None;
    }

    public void setAlignments(int columnIndex, Alignment alignment) {
        if (columnIndex >= columnNum || columnIndex < 0)
            throw new ArrayIndexOutOfBoundsException("`columnIndex` must be within [0, " + columnNum + "].");
        alignments[columnIndex] = alignment;
    }

    @Override
    public void addChild(MarkdownNode child) {
        if (!(child instanceof Row))
            throw new IllegalArgumentException("Child of a `Table` must be `Table.Row`.");
        super.addChild(child);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // Add table headers
        for (int i = 0; i < columnNum; i++) {
            builder.append("| ");
            if (!headers[i].isEmpty())
                builder.append(headers[i]).append(" ");
        }
        builder.append("|\n");
        // Add alignments
        for (int i = 0; i < columnNum; i++) {
            builder.append("| ");
            switch (alignments[i]) {
                case None: builder.append("--- "); break;
                case Left: builder.append(":--- "); break;
                case Center: builder.append(":---: "); break;
                case Right: builder.append("---: "); break;
            }
        }
        builder.append("|\n");
        // Add rows
        for (MarkdownNode node : getChildren()) {
            Row row = (Row) node;
            int i;
            for (i = 0; i < row.entries.length && i < columnNum; i++)
                builder.append("| ").append(row.entries[i]).append(' ');
            for (; i < columnNum; i++)
                builder.append("| ");
            builder.append("|\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    public static class Row extends MarkdownLeafNode {
        private final String[] entries;

        public Row(String... entries) {
            this.entries = new String[entries.length];
            for (int i = 0; i < entries.length; i++)
                this.entries[i] = entries[i].replaceAll("[\n\r]", "").replace("|", "\\|").trim();
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("");
        }
    }

    public enum Alignment {
        None, Left, Center, Right;
    }
}
