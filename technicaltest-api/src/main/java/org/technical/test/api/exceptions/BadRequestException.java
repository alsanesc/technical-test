package org.technical.test.api.exceptions;

import org.springframework.validation.Errors;

public class BadRequestException extends Exception implements ErrorAware {

    private final Errors errors;

    public BadRequestException() {
        super();
        this.errors = null;
    }

    public BadRequestException(final String message) {
        super(message);
        this.errors = null;
    }

    public BadRequestException(final Errors errors) {
        this.errors = errors;
    }

    public BadRequestException(final String message, final Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }

}
