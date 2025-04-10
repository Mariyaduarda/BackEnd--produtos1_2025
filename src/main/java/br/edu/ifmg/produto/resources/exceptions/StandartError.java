package br.edu.ifmg.produto.resources.exceptions;

import java.time.Instant;

public class StandartError {
    private Instant timestamp;
    private int Integer;
    private String error;
    private String message;
    private String path;

    public StandartError() {
    }

    public StandartError(Instant timestamp) {
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
        this.path = path;
        this.Integer = Integer;
        this.timestamp = Instant.now();

    }
    public StandartError(String error) {
        this.error = error;
    }

}
