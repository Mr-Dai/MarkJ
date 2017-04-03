package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HorizontalRuleTest {
    @Test
    public void testToString() throws Exception {
        assertThat(HorizontalRule.get().toString(), is("---\n\n"));
    }
}