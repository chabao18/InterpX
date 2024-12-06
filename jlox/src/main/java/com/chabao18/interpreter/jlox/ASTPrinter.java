package com.chabao18.interpreter.jlox;

public class ASTPrinter implements Expr.Visitor<String> {
    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1, 1, 1),
                        new Expr.Literal(123)
                ),
                new Token(TokenType.STAR, "*", null, 1, 1, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)
                )
        );

        ASTPrinter astPrinter = new ASTPrinter();
        String str = astPrinter.sprint(expression);
        System.out.println(str);

    }

    String sprint(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return null;
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return "(" + expr.left.accept(this) + " " + expr.operator.lexeme + " " + expr.right.accept(this) + ")";
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return "(" + expr.expression.accept(this) + ")";
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        return expr.value.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return null;
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return "(" + expr.operator.lexeme + expr.right.accept(this) + ")";
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        // todo
        return null;
    }
}
