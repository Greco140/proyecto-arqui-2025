package com.example.concesionaria.exception;

/**
 * Excepción personalizada de tiempo de ejecución.
 * Se lanza cuando se intenta acceder a un vehículo que no existe en la base de datos.
 */
public class VehiculoNotFoundException extends RuntimeException {

    /**
     * Constructor de la excepción.
     * * @param message Mensaje descriptivo del error.
     */
    public VehiculoNotFoundException(String message) {
        super(message);
    }
}