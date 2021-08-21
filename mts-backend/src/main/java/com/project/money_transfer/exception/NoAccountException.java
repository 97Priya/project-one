package com.project.money_transfer.exception;

public class NoAccountException extends RuntimeException {
    public NoAccountException(String message) {
        super(message);
    }
}
