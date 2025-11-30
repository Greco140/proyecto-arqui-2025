package com.example.concesionaria.service;

import com.example.concesionaria.exception.VehiculoNotFoundException;
import com.example.concesionaria.model.Vehiculo;
import com.example.concesionaria.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que encapsula la lógica de negocio para la gestión de vehículos.
 * Actúa como intermediario entre el controlador y el repositorio.
 */
@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    /**
     * Recupera todos los vehículos registrados en la base de datos.
     * * @return Una lista de objetos {@link Vehiculo}.
     */
    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Guarda un nuevo vehículo o actualiza uno existente.
     * * @param vehiculo El objeto vehículo a persistir.
     */
    public void guardar(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
    }

    /**
     * Busca un vehículo por su identificador único.
     * * @param id El ID del vehículo a buscar.
     * @return El objeto {@link Vehiculo} encontrado.
     * @throws VehiculoNotFoundException Si no existe un vehículo con ese ID.
     */
    public Vehiculo obtenerPorId(Long id) {
        Optional<Vehiculo> optional = vehiculoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new VehiculoNotFoundException("El vehículo con ID " + id + " no fue encontrado.");
        }
    }

    /**
     * Elimina un vehículo por su identificador.
     * * @param id El ID del vehículo a eliminar.
     * @throws VehiculoNotFoundException Si el ID proporcionado no existe.
     */
    public void eliminar(Long id) {
        if (vehiculoRepository.existsById(id)) {
            vehiculoRepository.deleteById(id);
        } else {
            throw new VehiculoNotFoundException("No se puede eliminar: El ID " + id + " no existe.");
        }
    }

    /**
     * Elimina múltiples vehículos en una sola operación.
     * Utilizado para la eliminación masiva desde la tabla.
     * * @param ids Lista de identificadores de los vehículos a eliminar.
     */
    public void eliminarMultiples(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            vehiculoRepository.deleteAllById(ids);
        }
    }

    /**
     * Busca vehículos que contengan la palabra clave en su modelo.
     * @param palabraClave Texto a buscar.
     * @return Lista de vehículos coincidentes.
     */
    public List<Vehiculo> buscarPorModelo(String palabraClave) {
        return vehiculoRepository.findByModeloContainingIgnoreCase(palabraClave);
    }
}