package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LineBreakTest {
    @Test
    public void testToString() throws Exception {
        assertThat(LineBreak.get().toString(), is("    \n"));
    }
}