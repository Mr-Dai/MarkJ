package com.mrdai.markj.parser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import java.util.LinkedList;
import java.util.function.Predicate;

public class NodePredicates {

    public static String cssSelector(Element element) {
        if (StringUtils.isNotBlank(element.id()))
            return "#" + element.id();
        StringBuilder builder = new StringBuilder(element.tagName());
        if (StringUtils.isNotBlank(element.className()))
            for (String className : element.classNames())
                builder.append('.').append(className);

        while (element.parent() != null) {
            element = element.parent();
            builder.insert(0, " > ");
            if (StringUtils.isNotBlank(element.id())) {
                builder.insert(0, "#" + element.id());
                break;
            }
            StringBuilder cBuilder = new StringBuilder(element.tagName());
            if (StringUtils.isNotBlank(element.className()))
                for (String className : element.classNames())
                    cBuilder.append('.').append(className);
            builder.insert(0, cBuilder.toString());
        }

        return builder.toString();
    }

    public static Predicate<Element> hasTagName(String tagName) {
        return (e) -> e.tagName().equals(tagName);
    }

    public static Predicate<Element> hasClass(String className) {
        return (e) -> {
            for (String classs : e.classNames())
                if (classs.equals(className))
                    return true;
            return false;
        };
    }

    public static Predicate<Element> hasId(String id) {
        return (e) -> e.id().equals(id);
    }

    public static Predicate<Element> hasDirectParent(Predicate<Element> parentPredicate) {
        return (e) -> e.parent() != null && parentPredicate.test(e.parent());
    }

    public static Predicate<Element> hasParent(Predicate<Element> parentPredicate) {
        return (e) -> {
            while (e.parent() != null) {
                e = e.parent();
                if (parentPredicate.test(e))
                    return true;
            }
            return false;
        };
    }

    public static Predicate<Element> hasDirectChild(Predicate<Element> childPredicate) {
        return (e) -> {
            for (Element child : e.children())
                if (childPredicate.test(child))
                    return true;
            return false;
        };
    }

    public static Predicate<Element> hasChild(Predicate<Element> childPredicate) {
        return (e) -> {
            LinkedList<Element> queue = new LinkedList<>();
            queue.addAll(e.children());
            while (!queue.isEmpty()) {
                Element child = queue.poll();
                if (childPredicate.test(child)) {
                    queue.clear();
                    return true;
                }
                queue.addAll(child.children());
            }
            return false;
        };
    }
}
