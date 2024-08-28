package formatter.rules;

import token.Token;

import java.util.List;

public class LinebreakBeforePrint implements PrintRule {
    @Override
    public List<Token> accept(RuleVisitor visitor, List<Token> tokens) {
        return visitor.visit(this, tokens);
    }
}
