package linter;

import AST.nodes.ASTNode;
import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import parser.iterator.ASTIterator;
import token.Token;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class LinterRunner {
  public void linterRun(String filePath, String configRulesPath, String version) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new FileInputStream(filePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> nodes = new ASTIterator(tokens, version);
    Linter linter = new Linter();
    while (nodes.hasNext()) {
      ASTNode node = nodes.next();
      linter.lint(node, configRulesPath);
    }
  }
}
