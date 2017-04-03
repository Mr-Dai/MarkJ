package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StrikeThroughTextTest {
    @Test
    public void testToString() throws Exception {
        StrikeThroughText strikeThroughText = new StrikeThroughText("This is a deleted text.");
        assertThat(strikeThroughText.toString(), is("~~This is a deleted text.~~"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithNullText() {
        StrikeThroughText strikeThroughText = new StrikeThroughText(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringWithEmptyText() {
        StrikeThroughText strikeThroughText = new StrikeThroughText("");
    }
}