package parser.syntax.result;

import ast.nodes.AstNode;
import parser.syntax.TokenStream;

public record ExpressionResult(AstNode astNode, TokenStream tokenStream) {
}