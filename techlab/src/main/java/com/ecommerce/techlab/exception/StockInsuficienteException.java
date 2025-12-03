package com.ecommerce.techlab.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }
    public StockInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
