package com.mrdai.markj;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CodeBlockTest {

    @Test
    public void testToStringWithoutLang() {
        CodeBlock codeBlock = new CodeBlock(StringUtils.join(Arrays.asList(
                "language: java",
                "jdk:",
                "  - oraclejdk8"
        ), "\n"));
        assertThat(codeBlock.toString(), is(StringUtils.join(Arrays.asList(
                "```",
                "language: java",
                "jdk:",
                "  - oraclejdk8",
                "```",
                "",
                ""
        ), "\n")));
    }

    @Test
    public void testToStringWithLang() {
        CodeBlock codeBlock = new CodeBlock("java", StringUtils.join(Arrays.asList(
                "public V get(Object key) {",
                "    Entry<K, V> p = getEntry(key);",
                "    return (p == null ? null : p.value);",
                "}"
        ), "\n"));
        assertThat(codeBlock.toString(), is(StringUtils.join(Arrays.asList(
                "```java",
                "public V get(Object key) {",
                "    Entry<K, V> p = getEntry(key);",
                "    return (p == null ? null : p.value);",
                "}",
                "```",
                "",
                ""
        ), "\n")));
    }
}
