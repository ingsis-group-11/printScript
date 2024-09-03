package formatter.rules.identifier;

import formatter.rules.visitor.RuleVisitor;
import token.Token;

import java.util.List;

public class Identifier implements IdentifierRule {
    @Override
    public List<Token> accept(RuleVisitor visitor, List<Token> tokens) {
        return visitor.visit(this, tokens);
    }
}
