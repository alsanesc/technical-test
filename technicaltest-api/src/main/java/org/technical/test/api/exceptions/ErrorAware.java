package org.technical.test.api.exceptions;

import org.springframework.validation.Errors;

public interface ErrorAware {

    Errors getErrors();

}
