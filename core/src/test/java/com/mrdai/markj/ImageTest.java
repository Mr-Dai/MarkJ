package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ImageTest {
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullLink() {
        Image image = new Image(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithEmptyLink() {
        Image image = new Image("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithBlankLink() {
        Image image = new Image("  \n\r");
    }

    @Test
    public void testToStringWithEmptyAltTextAndOptionalTitle() {
        Image image = new Image("https://avatars0.githubusercontent.com/u/6038522");
        assertThat(image.toString(), is("![](https://avatars0.githubusercontent.com/u/6038522)\n\n"));
    }

    @Test
    public void testToStringWithEmptyOptionalTitle() {
        Image image = new Image("https://avatars0.githubusercontent.com/u/6038522", "My avatar", "");
        assertThat(image.toString(), is("![My avatar](https://avatars0.githubusercontent.com/u/6038522)\n\n"));
    }

    @Test
    public void testToStringWithEmptyAltText() {
        Image image = new Image("https://avatars0.githubusercontent.com/u/6038522", "", "Mr.Dai's avatar");
        assertThat(image.toString(), is("![](https://avatars0.githubusercontent.com/u/6038522 \"Mr.Dai's avatar\")\n\n"));
    }

    @Test
    public void testToStringWithFullInfo() {
        Image image = new Image("https://avatars0.githubusercontent.com/u/6038522", "My avatar", "Mr.Dai's avatar");
        assertThat(image.toString(), is("![My avatar](https://avatars0.githubusercontent.com/u/6038522 \"Mr.Dai's avatar\")\n\n"));
    }
}