package com.github.paicoding.forum.api.model.exception;

public class NoVlaInGuavaException extends RuntimeException {
    public NoVlaInGuavaException(String msg) {
        super(msg);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}