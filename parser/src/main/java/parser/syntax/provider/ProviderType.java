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
  },
  READ_INPUT {
    @Override
    public SyntaxParserProvider getProvider() {
      return new ReadInputSyntaxParserProvider();
    }
  };

  public abstract SyntaxParserProvider getProvider();
}