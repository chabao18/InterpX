package com.chabao18.interpreter.jlox.runtime;

import com.chabao18.interpreter.jlox.core.Token;

public class RuntimeError extends RuntimeException {
    public final Token token;

    public RuntimeError(Token token, String message) {
        super(message);
        this.token = token;
    }
}
