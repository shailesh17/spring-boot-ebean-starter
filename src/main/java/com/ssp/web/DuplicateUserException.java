package com.ssp.web;

/**
 * @author shailesh.patel
 */
public class DuplicateUserException extends AppException {

    private static final long serialVersionUID = 1L;

    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserException(String message) {
        super(message);
    }

}
