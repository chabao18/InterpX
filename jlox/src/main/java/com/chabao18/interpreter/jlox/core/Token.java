package com.chabao18.interpreter.jlox.core;

public class Token {
    final TokenType type;
    public final String lexeme;
    final Object literal;
    final int row;
    final int offset;
    final int length;

    Token(TokenType type, String lexeme, Object literal, int row, int offset, int length) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.row = row;
        this.offset = offset;
        this.length = length;
    }

    public String toString() {
        return String.format("%-20s%-15s %-15s %s",
                String.format("[%d:%d (%d chars)]", row, offset, length),
                type, lexeme, literal);

    }
}