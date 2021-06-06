package br.com.renanfretta.vehiclead.api.commons;

public abstract class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

}
