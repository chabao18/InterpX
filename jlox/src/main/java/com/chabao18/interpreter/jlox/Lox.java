package com.chabao18.interpreter.jlox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {

    // make the interpreter static so that it can store global state
    private static final Interpreter interpreter = new Interpreter();
    static boolean hadError = false;
    static boolean hadRuntimeError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }


    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) {
            System.exit(65);
        }
        if (hadRuntimeError) {
            System.exit(70);
        }
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (; ; ) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            run(line);
            // won't interrupt the program even if there is an error
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();
//        for (Token token : tokens) {
//            System.out.println(token);
//        }

        // no need to report runtime error when using REPL
        if (hadError) {
            return;
        }

        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();


        interpreter.interpret(statements);
    }

    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.row, token.offset, "at end", message);
        } else {
            report(token.row, token.offset, "at '" + token.lexeme + "'", message);
        }
    }

    static void error(int row, int col, String format, Object... args) {
        String message = String.format(format, args);
        report(row, col, "", message);
    }

    private static void report(int row, int col, String where, String message) {
        System.err.printf("[%d:%d] Error %s: %s\n", row, col, where, message);
        hadError = true;
    }

    static void runtimeError(RuntimeError error) {
        System.err.printf("[%d:%d] RuntimeError: %s\n", error.token.row, error.token.offset, error.getMessage());
        hadRuntimeError = true;
    }


}
