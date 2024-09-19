package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.BlockNode;
import ast.nodes.IfNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.factory.SyntaxParserFactory;
import parser.syntax.provider.ProviderTypeV2;
import token.TokenType;

public class IfSyntaxParser implements SyntaxParser {

  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {

    handleExpect(tokens.expect(TokenType.IF_KEYWORD, "Expected 'if'"));
    tokens.advance();

    handleExpect(tokens.expect(TokenType.PARENTHESIS_OPEN, "Expected '('"));
    tokens.advance();

    AstNode condition = ExpressionFactory.createExpression(tokens, version);

    handleExpect(tokens.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'"));
    tokens.advance();

    handleExpect(tokens.expect(TokenType.BRACE_OPEN, "Expected '{'"));
    tokens.advance();

    BlockNode ifBlock = parseBlock(tokens, version);

    handleExpect(tokens.expect(TokenType.BRACE_CLOSE, "Expected '}'"));
    tokens.advance();

    BlockNode elseBlock = new BlockNode(new ArrayList<>());

    if (tokens.hasNext()) {

      if (tokens.getCurrentToken().getType() == TokenType.LINE_BREAK) {
        tokens.advance();
      }

      if (tokens.getCurrentToken().getType() == TokenType.ELSE_KEYWORD) {

        tokens.advance();

        handleExpect(tokens.expect(TokenType.BRACE_OPEN, "Expected '{'"));
        tokens.advance();

        elseBlock = parseBlock(tokens, version);

        handleExpect(tokens.expect(TokenType.BRACE_CLOSE, "Expected '}'"));
        tokens.advance();
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

  private BlockNode parseBlock(TokenStream tokens, String version) {
    List<AstNode> block = new ArrayList<>();
    SyntaxParserFactory syntaxParserFactory =
        new SyntaxParserFactory(Set.of(ProviderTypeV2.values()));
    while (tokens.getCurrentToken().getType() != TokenType.BRACE_CLOSE) {
      SyntaxParser parser = syntaxParserFactory.getSyntaxParser(tokens);
      AstNode result = parser.syntaxParse(tokens, version);
      block.add(result);
    }
    return new BlockNode(block);
  }

  private void handleExpect(Optional<Exception> exception) {
    exception.ifPresent(
        e -> {
          if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
          } else {
            throw new RuntimeException(e);
          }
        });
  }
}
