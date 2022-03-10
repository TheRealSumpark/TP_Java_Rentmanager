package com.epf.rentmanager.dao.exception;

public class ServiceException extends Exception {
    public ServiceException() {
        super("Erreur dans Service");
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
