package com.github.paicoding.forum.core.markdown;

import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.admonition.AdmonitionBlock;
import com.vladsch.flexmark.ext.admonition.internal.AdmonitionOptions;
import com.vladsch.flexmark.parser.block.*;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomAdmonitionBlockParser extends AbstractBlockParser {
    final private static String ADMONITION_START_FORMAT = "^(\\?{3}\\+|\\?{3}|!{3}|:{3})\\s*(%s)(?:\\s+(%s))?\\s*$";

    final AdmonitionBlock block;
    //private BlockContent content = new BlockContent();
    final private AdmonitionOptions options;
    final private int contentIndent;
    private boolean hadBlankLine;
    private boolean isOver;

    CustomAdmonitionBlockParser(AdmonitionOptions options, int contentIndent) {
        this.options = options;
        this.contentIndent = contentIndent;
        this.block = new AdmonitionBlock();
    }

    private int getContentIndent()  { return 0; }

    @Override
    public Block getBlock()  { return null; }

    @Override
    public boolean isContainer()  { return false; }

    @Override
    public boolean canContain(ParserState state, BlockParser blockParser, final Block block)  { return false; }

    @Override
    public BlockContinue tryContinue(ParserState state)  { return null; }

    @Override
    public void closeBlock(ParserState state)  {}

    public static class Factory implements CustomBlockParserFactory {
        @Nullable
        @Override
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Nullable
        @Override
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override
        public @Nullable SpecialLeadInHandler getLeadInHandler(@NotNull DataHolder options) {
            return CustomAdmonitionBlockParser.AdmonitionLeadInHandler.HANDLER;
        }

        @Override
        public boolean affectsGlobalScope()  { return false; }

        @NotNull
        @Override
        public BlockParserFactory apply(@NotNull DataHolder options)  { return null; }
    }

    static class AdmonitionLeadInHandler implements SpecialLeadInHandler {
        final static SpecialLeadInHandler HANDLER = new CustomAdmonitionBlockParser
                .AdmonitionLeadInHandler();

        @Override
        public boolean escape(@NotNull BasedSequence sequence, @Nullable DataHolder options, @NotNull Consumer<CharSequence> consumer)  { return false; }

        @Override
        public boolean unEscape(@NotNull BasedSequence sequence, @Nullable DataHolder options, @NotNull Consumer<CharSequence> consumer)  { return false; }
    }

    static boolean isMarker(
            final ParserState state,
            final int index,
            final boolean inParagraph,
            final boolean inParagraphListItem,
            final AdmonitionOptions options
    ) {
        final boolean allowLeadingSpace = options.allowLeadingSpace;
        final boolean interruptsParagraph = options.interruptsParagraph;
        final boolean interruptsItemParagraph = options.interruptsItemParagraph;
        final boolean withLeadSpacesInterruptsItemParagraph = options.withSpacesInterruptsItemParagraph;
        CharSequence line = state.getLine();
        if (!inParagraph || interruptsParagraph) {
            if ((allowLeadingSpace || state.getIndent() == 0) && (!inParagraphListItem || interruptsItemParagraph)) {
                if (inParagraphListItem && !withLeadSpacesInterruptsItemParagraph) {
                    return state.getIndent() == 0;
                } else {
                    return state.getIndent() < state.getParsing().CODE_BLOCK_INDENT;
                }
            }
        }
        return false;
    }

    private static class BlockFactory extends AbstractBlockParserFactory {
        final private AdmonitionOptions options;

        BlockFactory(DataHolder options) {
            super(options);
            this.options = new AdmonitionOptions(options);
        }

        @Override
        public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
            if (state.getIndent() >= 4) {
                return BlockStart.none();
            }

            int nextNonSpace = state.getNextNonSpaceIndex();
            BlockParser matched = matchedBlockParser.getBlockParser();
            boolean inParagraph = matched.isParagraphParser();
            boolean inParagraphListItem = inParagraph && matched.getBlock().getParent() instanceof ListItem && matched.getBlock() == matched.getBlock().getParent().getFirstChild();

            if (isMarker(state, nextNonSpace, inParagraph, inParagraphListItem, options)) {
                BasedSequence line = state.getLine();
                BasedSequence trySequence = line.subSequence(nextNonSpace, line.length());
                Parsing parsing = state.getParsing();
                Pattern startPattern = Pattern.compile(String.format(ADMONITION_START_FORMAT, parsing.ATTRIBUTENAME, parsing.LINK_TITLE_STRING));
                Matcher matcher = startPattern.matcher(trySequence);

                if (matcher.find()) {
                    // admonition block
                    BasedSequence openingMarker = line.subSequence(nextNonSpace + matcher.start(1), nextNonSpace + matcher.end(1));
                    BasedSequence info = line.subSequence(nextNonSpace + matcher.start(2), nextNonSpace + matcher.end(2));
                    BasedSequence titleChars = matcher.group(3) == null ? BasedSequence.NULL : line.subSequence(nextNonSpace + matcher.start(3), nextNonSpace + matcher.end(3));

                    int contentOffset = options.contentIndent;

                    CustomAdmonitionBlockParser admonitionBlockParser = new CustomAdmonitionBlockParser (options, contentOffset);
                    admonitionBlockParser.block.setOpeningMarker(openingMarker);
                    admonitionBlockParser.block.setInfo(info);
                    admonitionBlockParser.block.setTitleChars(titleChars);

                    return BlockStart.of(admonitionBlockParser)
                            .atIndex(line.length());
                } else {
                    return BlockStart.none();
                }
            } else {
                return BlockStart.none();
            }
        }
    }
}
