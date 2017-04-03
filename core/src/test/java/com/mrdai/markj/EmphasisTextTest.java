package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EmphasisTextTest {
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullText() {
        EmphasisText emphasisText = new EmphasisText(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithEmptyText() {
        EmphasisText emphasisText = new EmphasisText("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithBlankText() {
        EmphasisText emphasisText = new EmphasisText("   ");
    }

    @Test
    public void testToStringUsingAsterisk() {
        EmphasisText emphasisText = new EmphasisText("  This is text \n");
        assertThat(emphasisText.toString(), is("*This is text*"));
        emphasisText = new EmphasisText(" This is text ", true);
        assertThat(emphasisText.toString(), is("*This is text*"));
    }

    @Test
    public void testToStringUsingUnderscore() {
        EmphasisText emphasisText = new EmphasisText("   This is text \r\n", false);
        assertThat(emphasisText.toString(), is("_This is text_"));
    }
}