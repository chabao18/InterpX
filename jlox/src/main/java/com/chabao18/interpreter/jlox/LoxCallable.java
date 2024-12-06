package com.chabao18.interpreter.jlox;

import java.util.List;

interface LoxCallable {
    /**
     * the number of arguments the function expects
     */
    int arity();

    Object call(Interpreter interpreter, List<Object> arguments);
}
