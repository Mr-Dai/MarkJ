package com.mrdai.markj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EmptyNodeTest {
    @Test
    public void testToString() throws Exception {
        assertThat(EmptyNode.get().toString().isEmpty(), is(true));
    }
}