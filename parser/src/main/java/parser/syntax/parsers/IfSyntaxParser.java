package parser.syntax.parsers;

import AST.nodes.ASTNode;
import AST.nodes.BlockNode;
import AST.nodes.IfNode;
import AST.nodes.LiteralNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.factory.SyntaxParserFactory;
import parser.syntax.provider.ProviderType1_1;
import token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IfSyntaxParser implements SyntaxParser {
  @Override
  public ASTNode syntaxParse(TokenStream tokens, String version) {

    tokens.expect(TokenType.IF_KEYWORD, "Expected 'if'");

    tokens.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");

    ASTNode condition = ExpressionFactory.createExpression(tokens, version);
    if (!(condition instanceof LiteralNode)) {
      throw new RuntimeException("Condition must be a boolean literal expression");
    }

    tokens.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");

    tokens.expect(TokenType.BRACE_OPEN, "Expected '{'");

    BlockNode ifBlock = parseBlock(tokens, version);

    tokens.expect(TokenType.BRACE_CLOSE, "Expected '}'");

    BlockNode elseBlock = new BlockNode(new ArrayList<>());
    if (tokens.hasNext()) {

      if (tokens.getCurrentToken().getType() == TokenType.ELSE_KEYWORD) {

        tokens.advance();

        tokens.expect(TokenType.BRACE_OPEN, "Expected '{'");

        elseBlock = parseBlock(tokens, version);

        tokens.expect(TokenType.BRACE_CLOSE, "Expected '}'");
      }

    }

    IfNode ifNode = new IfNode(
        (LiteralNode) condition,
        ifBlock,
        elseBlock,
        tokens.getLastToken().getLine(),
        tokens.getLastToken().getColumn()
    );

    return ifNode;
  }

  private BlockNode parseBlock(TokenStream tokens, String version) {
    List<ASTNode> block = new ArrayList<>();
    SyntaxParserFactory syntaxParserFactory = new SyntaxParserFactory(Set.of(ProviderType1_1.values()));
    while (tokens.getCurrentToken().getType() != TokenType.BRACE_CLOSE) {
      SyntaxParser parser = syntaxParserFactory.getSyntaxParser(tokens);
      ASTNode result = parser.syntaxParse(tokens, version);
      block.add(result);
    }
    return new BlockNode(block);
  }
}
