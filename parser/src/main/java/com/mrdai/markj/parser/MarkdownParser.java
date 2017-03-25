package com.mrdai.markj.parser;

import com.mrdai.markj.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.mrdai.markj.parser.NodePredicates.hasTagName;

public class MarkdownParser {
    private List<PredicateAction> actions = new LinkedList<>();
    private boolean fixed = false;

    public MarkdownParser() {
        appendAction(
            (node) -> node instanceof TextNode,
            (node) -> {
                if (StringUtils.isBlank(((TextNode) node).text()))
                    return EmptyNode.get();
                return new PlainText(((TextNode) node).text());
            });
        appendElementAction(hasTagName("p"), (e) -> {
            ParagraphNode p = new ParagraphNode();
            for (Node node : e.childNodes())
                p.addChild(parse(node));
            return p;
        });

        appendElementAction(hasTagName("ul"), (e) -> {
            UnorderedList list = new UnorderedList('-');
            for (Element item : e.select("li"))
                list.addChild(parse(item));
            return list;
        });
        appendElementAction(hasTagName("ol"), (e) -> {
            OrderedList list = new OrderedList(1);
            for (Element item : e.select("li"))
                list.addChild(parse(item));
            return list;
        });
        appendElementAction(hasTagName("li"), (e) -> {
            MarkdownNode entry = new MarkdownNode();
            for (Node item : e.childNodes())
                entry.addChild(parse(item));
            return entry;
        });
        appendElementAction(hasTagName("pre"), (e) -> new CodeBlock(e.text()));
        appendElementAction(hasTagName("img"), (e) -> {
            String src = e.attr("src");
            if (src.startsWith("/")) {
                URL baseUrl;
                try {
                    baseUrl = new URL(e.baseUri());
                } catch (MalformedURLException ex) {
                    throw new IllegalArgumentException("Fuck!", ex);
                }

                src = String.format("%s://%s%s", baseUrl.getProtocol(), baseUrl.getHost(), src);
            }
            return new Image(src, e.attr("alt"), "");
        });
        appendElementAction(hasTagName("code"), (e) -> new InlineCode(e.text()));
        appendElementAction(hasTagName("em").or(hasTagName("i")), (e) -> new EmphasisText(e.text()));
        appendElementAction(hasTagName("strong").or(hasTagName("b")), (e) -> new StrongText(e.text()));
        appendElementAction(hasTagName("h1"), (e) -> new Header(1, e.text()));
        appendElementAction(hasTagName("h2"), (e) -> new Header(2, e.text()));
        appendElementAction(hasTagName("h3"), (e) -> new Header(3, e.text()));
        appendElementAction(hasTagName("h4"), (e) -> new Header(4, e.text()));
        appendElementAction(hasTagName("h5"), (e) -> new Header(5, e.text()));
        appendElementAction(hasTagName("h6"), (e) -> new Header(6, e.text()));
        appendElementAction(hasTagName("hr"), (e) -> HorizontalRule.get());
        appendElementAction(hasTagName("br"), (e) -> LineBreak.get());
        appendElementAction(hasTagName("code"), (e) -> new InlineCode(e.text()));
        appendElementAction(hasTagName("a").and(Element::hasText), (e) -> {
            String href = e.attr("href");
            if (href.startsWith("/")) {
                URL baseUrl;
                try {
                    baseUrl = new URL(e.baseUri());
                } catch (MalformedURLException ex) {
                    throw new IllegalArgumentException("Fuck!", ex);
                }

                href = String.format("%s://%s%s", baseUrl.getProtocol(), baseUrl.getHost(), href);
            }
            return new LinkNode(e.text(), href);
        });
    }

    public void ignore(Predicate<Node> predicate) {
        prependAction(predicate, (n) -> EmptyNode.get());
    }

    public void ignoreElement(Predicate<Element> predicate) {
        prependElementAction(predicate, (e) -> EmptyNode.get());
    }

    public void prependAction(Predicate<Node> predicate, Function<Node, MarkdownNode> action) {
        if (fixed)
            throw new IllegalStateException("The com.mrdai.markj.parser is already fixed and cannot add any other action.");
        actions.add(0, new PredicateAction(predicate, action));
    }

    public void prependElementAction(Predicate<Element> predicate, Function<Element, MarkdownNode> action) {
        if (fixed)
            throw new IllegalStateException("The com.mrdai.markj.parser is already fixed and cannot add any other action.");
        actions.add(0, new PredicateAction(
            (node) -> node instanceof Element && predicate.test((Element) node),
            (node) -> action.apply((Element) node))
        );
    }

    public void appendAction(Predicate<Node> predicate, Function<Node, MarkdownNode> action) {
        if (fixed)
            throw new IllegalStateException("The com.mrdai.markj.parser is already fixed and cannot add any other action.");
        actions.add(new PredicateAction(predicate, action));
    }

    public void appendElementAction(Predicate<Element> predicate, Function<Element, MarkdownNode> action) {
        if (fixed)
            throw new IllegalStateException("The com.mrdai.markj.parser is already fixed and cannot add any other action.");
        actions.add(new PredicateAction(
            (node) -> node instanceof Element && predicate.test((Element) node),
            (node) -> action.apply((Element) node))
        );
    }

    public boolean hasMatch(Node e) {
        for (PredicateAction action : actions)
            if (action.test(e))
                return true;
        return false;
    }

    public synchronized void fix() {
        appendAction((n) -> true, (n) -> EmptyNode.get());
        actions = Collections.unmodifiableList(actions);
        fixed = true;
    }

    public MarkdownNode parse(Node element) {
        if (!fixed)
            throw new IllegalStateException("The com.mrdai.markj.parser is not fixed yet.");
        for (PredicateAction action : actions)
            if (action.test(element))
                return action.apply(element);
        throw new AssertionError("Shouldn't reach here. Check if something wrong.");
    }

    private static class PredicateAction {
        private final Predicate<Node> predicate;
        private final Function<Node, MarkdownNode> action;

        private PredicateAction(Predicate<Node> predicate,
                                Function<Node, MarkdownNode> action) {
            this.predicate = predicate;
            this.action = action;
        }

        private boolean test(Node e) {
            return predicate.test(e);
        }

        private MarkdownNode apply(Node e) {
            return action.apply(e);
        }
    }
}
