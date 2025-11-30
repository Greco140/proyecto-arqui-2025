package com.example.concesionaria.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Representa la entidad Vehículo en la base de datos.
 * Mapea la tabla "vehiculos" y define sus atributos principales.
 * * @author Greco
 * @version 1.0
 */
@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    /**
     * Identificador único del vehículo (Clave Primaria).
     * Se genera automáticamente mediante estrategia IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Modelo o descripción del vehículo.
     * Ejemplo: "Toyota Corolla 2024".
     * No puede ser nulo.
     */
    @Column(nullable = false, length = 100)
    private String modelo;

    /**
     * Precio del vehículo.
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private Double precio;
}