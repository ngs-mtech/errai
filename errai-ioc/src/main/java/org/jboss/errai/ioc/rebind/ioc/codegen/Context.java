package org.jboss.errai.ioc.rebind.ioc.codegen;

import java.util.LinkedList;

import org.jboss.errai.ioc.rebind.ioc.codegen.builder.values.LiteralFactory;

/**
 * This class represents a {@link Statement} context. It's basically
 * a simple stack of statements which is used to ensure that referenced 
 * statements/variables are in scope.
 * 
 * @author Christian Sadilek <csadilek@redhat.com>
 */
public class Context {
    protected LinkedList<Statement> stack = new LinkedList<Statement>();

    private Context() {}
    
    public static Context create() {
        return new Context();
    }
    
    public Context push(Statement statement) {
        stack.addFirst(statement);
        return this;
    }
    
    public Context push(Object object) {
        stack.addFirst(LiteralFactory.getLiteral(object));
        return this;
    }
    
    public Statement peek() {
        return stack.peekFirst();
    }
    
    public boolean contains(Statement var) {
        return stack.indexOf(var)!=-1;
    }
    
    public void merge(Context scope) {
        this.stack.addAll(stack.size(), scope.stack);
    }
}
