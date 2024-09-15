package parser.syntax.result;

import parser.syntax.TokenStream;
import parser.syntax.parsers.SyntaxParser;

public record SyntaxParserFactoryResult(SyntaxParser syntaxParser, TokenStream tokenStream) {

}