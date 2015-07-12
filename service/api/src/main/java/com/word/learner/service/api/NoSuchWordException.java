package com.word.learner.service.api;

public class NoSuchWordException extends Exception {
    public NoSuchWordException(String message) {
        super(message);
    }
}
