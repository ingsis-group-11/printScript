package parser.syntax.provider;

public enum ProviderType1_1 implements ProviderType {
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

  @Override
  public abstract SyntaxParserProvider getProvider();
}