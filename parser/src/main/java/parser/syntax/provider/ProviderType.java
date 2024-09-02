package parser.syntax.provider;

public enum ProviderType {
  ASSIGNATION {
    @Override
    public SyntaxParserProvider getProvider() {
      return new AssignationSyntaxParserProvider();
    }
  },
  REASSIGNATION {
    @Override
    public SyntaxParserProvider getProvider() {
      return new ReassignationSyntaxParserProvider();
    }
  },
  PRINT {
    @Override
    public SyntaxParserProvider getProvider() {
      return new PrintSyntaxParserProvider();
    }
  };

  public abstract SyntaxParserProvider getProvider();
}