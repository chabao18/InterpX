package com.chabao18.interpreter.jlox;

import java.util.HashMap;
import java.util.Map;

public class LoxInstance {
    private LoxClass kclass;
    private final Map<String, Object> fields = new HashMap<>();

    LoxInstance(LoxClass kclass) {
        this.kclass = kclass;
    }

    @Override
    public String toString() {
        return kclass.name + " instance";
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }
}
