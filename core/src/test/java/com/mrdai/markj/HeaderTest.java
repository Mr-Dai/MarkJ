package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HeaderTest {
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullText() {
        Header header = new Header(2, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithEmptyText() {
        Header header = new Header(2, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithBlankText() {
        Header header = new Header(2, "   \n\r");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithOverSizedLevel() {
        Header header = new Header(10, "A header");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithUnderSizedLevel() {
        Header header = new Header(0, "A header");
    }

    @Test
    public void testToString() {
        Header header = new Header(1, "A header");
        assertThat(header.toString(), is("# A header\n\n"));
        header = new Header(2, "A header\n");
        assertThat(header.toString(), is("## A header\n\n"));
        header = new Header(3, "A header\r\n");
        assertThat(header.toString(), is("### A header\n\n"));
        header = new Header(4, "A header  ");
        assertThat(header.toString(), is("#### A header\n\n"));
        header = new Header(5, "A header");
        assertThat(header.toString(), is("##### A header\n\n"));
    }
}