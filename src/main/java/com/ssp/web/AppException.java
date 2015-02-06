package com.ssp.web;

/**
 * Global app exception
 * 
 * @author shailesh.patel
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 1L;

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
