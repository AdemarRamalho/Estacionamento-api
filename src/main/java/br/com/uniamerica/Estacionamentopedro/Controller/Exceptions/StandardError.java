package br.com.uniamerica.Estacionamentopedro.controller.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
public class StandardError implements Serializable {
    @Getter
    @Setter
    private Instant timestamp;
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private String error;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String path;
}
