package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.BlockNode;
import ast.nodes.IfNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.factory.SyntaxParserFactory;
import parser.syntax.provider.ProviderTypeV2;
import parser.syntax.result.ExpressionResult;
import parser.syntax.result.ParseBlockResult;
import parser.syntax.result.SyntaxParserFactoryResult;
import token.TokenType;

public class IfSyntaxParser implements SyntaxParser {
  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {

    tokens.expect(TokenType.IF_KEYWORD, "Expected 'if'");
    tokens = tokens.advance();

    tokens.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
    tokens = tokens.advance();
    ExpressionResult result = ExpressionFactory.createExpression(tokens, version);
    AstNode condition = result.astNode();
    tokens = result.tokenStream();

    tokens.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokens = tokens.advance();
    tokens.expect(TokenType.BRACE_OPEN, "Expected '{'");
    tokens = tokens.advance();
    ParseBlockResult ifBlockResult = parseBlock(tokens, version);
    BlockNode ifBlock = ifBlockResult.blockNode();
    tokens = ifBlockResult.tokenStream();

    tokens.expect(TokenType.BRACE_CLOSE, "Expected '}'");
    tokens = tokens.advance();
    BlockNode elseBlock = new BlockNode(new ArrayList<>());

    if (tokens.hasNext()) {

      if (tokens.getCurrentToken().getType() == TokenType.ELSE_KEYWORD) {

        tokens = tokens.advance();
        tokens.expect(TokenType.BRACE_OPEN, "Expected '{'");
        tokens = tokens.advance();
        elseBlock = parseBlock(tokens, version).blockNode();

        tokens.expect(TokenType.BRACE_CLOSE, "Expected '}'");
      }
    }

    IfNode ifNode =
        new IfNode(
            condition,
            ifBlock,
            elseBlock,
            tokens.getCurrentToken().getLine(),
            tokens.getCurrentToken().getColumn());

    return ifNode;
  }

  private ParseBlockResult parseBlock(TokenStream tokens, String version) {
    List<AstNode> block = new ArrayList<>();
    SyntaxParserFactory syntaxParserFactory =
        new SyntaxParserFactory(Set.of(ProviderTypeV2.values()));
    while (tokens.getCurrentToken().getType() != TokenType.BRACE_CLOSE) {
      SyntaxParserFactoryResult result = syntaxParserFactory.getSyntaxParser(tokens);
      tokens = result.tokenStream();
      SyntaxParser parser = result.syntaxParser();
      AstNode node = parser.syntaxParse(tokens, version);
      block.add(node);
      tokens = tokens.advance();
    }
    return new ParseBlockResult(new BlockNode(block), tokens);
  }
}
