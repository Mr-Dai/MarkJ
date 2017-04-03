package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InlineCodeTest {
    @Test
    public void testToString() throws Exception {
        InlineCode inlineCode = new InlineCode("println \"Hello world!\"");
        assertThat(inlineCode.toString(), is("`println \"Hello world!\"`"));
    }

    @Test
    public void testToStringWithNewLineCharacters() throws Exception {
        InlineCode inlineCode = new InlineCode("println\r\n\"Hello\rworld!\"\n");
        assertThat(inlineCode.toString(), is("`println \"Hello world!\"`"));
    }

    @Test
    public void testToStringWithInnerBacktick() throws Exception {
        InlineCode inlineCode = new InlineCode("println `Hello world!`");
        assertThat(inlineCode.toString(), is("`println \\`Hello world!\\``"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithNullText() {
        InlineCode inlineCode = new InlineCode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithEmptyText() {
        InlineCode inlineCode = new InlineCode("");
    }
}