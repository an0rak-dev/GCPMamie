package com.github.an0rakdev.talkgcpmamie.services;

public class ServiceException extends Exception {
    public ServiceException(String str) {
        super(str);
    }

    public ServiceException(String str, Throwable cause) {
        super(str, cause);
    }
}
