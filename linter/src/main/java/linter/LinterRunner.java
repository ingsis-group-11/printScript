package linter;

import AST.nodes.ASTNode;
import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import lexer.Lexer;
import parser.iterator.ASTIterator;
import parser.Parser;
import token.Token;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class LinterRunner {
  public void linterRun(String filePath, String configRulesPath) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, new Lexer());
    Iterator<ASTNode> nodes = new ASTIterator(new Parser(), tokens);
    Linter linter = new Linter();
    while (nodes.hasNext()) {
      ASTNode node = nodes.next();
      linter.lint(node, configRulesPath);
    }
  }
}
