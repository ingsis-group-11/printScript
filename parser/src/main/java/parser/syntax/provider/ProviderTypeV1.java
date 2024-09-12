package parser.syntax.provider;

public enum ProviderTypeV1 implements ProviderType {
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

  @Override
  public abstract SyntaxParserProvider getProvider();
}
