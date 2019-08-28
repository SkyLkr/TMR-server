package com.example.tmrserver.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Comando Inválido!");
    }
}
