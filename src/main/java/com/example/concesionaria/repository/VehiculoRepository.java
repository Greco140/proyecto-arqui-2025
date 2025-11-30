package com.example.concesionaria.repository;

import com.example.concesionaria.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interfaz de repositorio para la entidad Vehículo.
 * Extiende de JpaRepository para proporcionar operaciones CRUD estándar
 * sin necesidad de implementar código SQL manualmente.
 * * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // JpaRepository ya incluye métodos como save(), findAll(), findById(), deleteById(), etc.

    /**
     * Busca vehículos por modelo (búsqueda parcial, sin distinguir mayúsculas/minúsculas).
     * @param modelo El modelo del vehículo a buscar.
     * @return Lista de vehículos que coinciden con el modelo proporcionado.
     */
    List<Vehiculo> findByModeloContainingIgnoreCase(String modelo);
}