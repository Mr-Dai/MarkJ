package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LinkNodeTest {
    @Test
    public void testToString() throws Exception {
        LinkNode linkNode = new LinkNode("Mr-Dai", "https://github.com/Mr-Dai");
        assertThat(linkNode.toString(), is("[Mr-Dai](https://github.com/Mr-Dai)"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithNullText() {
        LinkNode linkNode = new LinkNode(null, "https://github.com/Mr-Dai");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithEmptyText() {
        LinkNode linkNode = new LinkNode("", "https://github.com/Mr-Dai");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithNullHref() {
        LinkNode linkNode = new LinkNode("Mr-Dai", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithEmptyHref() {
        LinkNode linkNode = new LinkNode("Mr-Dai", "");
    }
}