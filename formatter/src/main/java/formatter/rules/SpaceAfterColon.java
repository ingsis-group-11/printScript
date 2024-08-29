package formatter.rules;

import token.Token;

import java.util.List;

public class SpaceAfterColon implements ColonRule {
    @Override
    public List<Token> accept(RuleVisitor visitor, List<Token> tokens) {
        return visitor.visit(this, tokens);
    }
}