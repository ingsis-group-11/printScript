package parser.syntax.result;

import ast.nodes.BlockNode;
import parser.syntax.TokenStream;

public record ParseBlockResult(BlockNode blockNode, TokenStream tokenStream) {
}
