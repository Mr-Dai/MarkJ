package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StrongTextTest {
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullText() {
        StrongText strongText = new StrongText(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithEmptyText() {
        StrongText strongText = new StrongText("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithBlankText() {
        StrongText strongText = new StrongText("   ");
    }

    @Test
    public void testToStringUsingAsterisk() {
        StrongText strongText = new StrongText("  This is text \n");
        assertThat(strongText.toString(), is("**This is text**"));
        strongText = new StrongText(" This is text ", true);
        assertThat(strongText.toString(), is("**This is text**"));
    }

    @Test
    public void testToStringUsingUnderscore() {
        StrongText strongText = new StrongText("   This is text \r\n", false);
        assertThat(strongText.toString(), is("__This is text__"));
    }
}