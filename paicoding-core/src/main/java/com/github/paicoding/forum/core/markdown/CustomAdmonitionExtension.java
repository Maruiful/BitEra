package com.github.paicoding.forum.core.markdown;

import com.vladsch.flexmark.ext.admonition.AdmonitionBlock;

import com.vladsch.flexmark.ext.admonition.internal.AdmonitionNodeFormatter;
import com.vladsch.flexmark.ext.admonition.internal.AdmonitionNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Extension for admonitions
 * <p>
 * Create it with {@link #create()} and then configure it on the builders
 * <p>
 * The parsed admonition text is turned into {@link AdmonitionBlock} nodes.
 */
public class CustomAdmonitionExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension, Formatter.FormatterExtension
        // , Parser.ReferenceHoldingExtension
{
    final public static DataKey<Integer> CONTENT_INDENT = new DataKey<>("ADMONITION.CONTENT_INDENT", 4);
    final public static DataKey<Boolean> ALLOW_LEADING_SPACE = new DataKey<>("ADMONITION.ALLOW_LEADING_SPACE", true);
    final public static DataKey<Boolean> INTERRUPTS_PARAGRAPH = new DataKey<>("ADMONITION.INTERRUPTS_PARAGRAPH", true);
    final public static DataKey<Boolean> INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("ADMONITION.INTERRUPTS_ITEM_PARAGRAPH", true);
    final public static DataKey<Boolean> WITH_SPACES_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("ADMONITION.WITH_SPACES_INTERRUPTS_ITEM_PARAGRAPH", true);
    final public static DataKey<Boolean> ALLOW_LAZY_CONTINUATION = new DataKey<>("ADMONITION.ALLOW_LAZY_CONTINUATION", true);
    final public static DataKey<String> UNRESOLVED_QUALIFIER = new DataKey<>("ADMONITION.UNRESOLVED_QUALIFIER", "note");
    final public static DataKey<Map<String, String>> QUALIFIER_TYPE_MAP = new DataKey<>("ADMONITION.QUALIFIER_TYPE_MAP", CustomAdmonitionExtension::getQualifierTypeMap);
    final public static DataKey<Map<String, String>> QUALIFIER_TITLE_MAP = new DataKey<>("ADMONITION.QUALIFIER_TITLE_MAP", CustomAdmonitionExtension::getQualifierTitleMap);
    final public static DataKey<Map<String, String>> TYPE_SVG_MAP = new DataKey<>("ADMONITION.TYPE_SVG_MAP", CustomAdmonitionExtension::getQualifierSvgValueMap);

    public static Map<String, String> getQualifierTypeMap()  { return null; }

    public static Map<String, String> getQualifierTitleMap()  { return null; }

    public static Map<String, String> getQualifierSvgValueMap()  { return null; }

    public static String getInputStreamContent(InputStream inputStream)  { return null; }

    public static String getDefaultCSS()  { return null; }

    public static String getDefaultScript()  { return null; }

    public static void copy(Reader reader, Writer writer) throws IOException  {}

    private CustomAdmonitionExtension() {
    }

    public static CustomAdmonitionExtension create()  { return null; }

    @Override
    public void extend(Formatter.Builder formatterBuilder)  {}

    @Override
    public void rendererOptions(@NotNull MutableDataHolder options)  {}

    @Override
    public void parserOptions(MutableDataHolder options)  {}

    @Override
    public void extend(Parser.Builder parserBuilder)  {}

    @Override
    public void extend(@NotNull HtmlRenderer.Builder htmlRendererBuilder, @NotNull String rendererType)  {}
}
