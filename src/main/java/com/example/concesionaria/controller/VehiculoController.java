package com.example.concesionaria.controller;

import com.example.concesionaria.model.Vehiculo;
import com.example.concesionaria.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador Web (MVC) del sistema de concesionaria.
 * <p>
 * Esta clase maneja todas las peticiones HTTP realizadas por el navegador,
 * coordina la lógica de negocio con el servicio {@link VehiculoService} 
 * y determina qué vista (HTML) se debe mostrar al usuario.
 * </p>
 *
 * @author Greco
 * @version 1.1
 */
@Controller
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    /**
     * Maneja la petición GET a la página principal.
     * Carga el listado de vehículos, opcionalmente filtrado por una palabra clave.
     *
     * @param model        Objeto de Spring utilizado para pasar datos a la vista (HTML).
     * @param palabraClave (Opcional) Texto recibido desde el buscador para filtrar resultados.
     * @return El nombre de la plantilla HTML a renderizar ("index").
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "palabraClave", required = false) String palabraClave) {
        List<Vehiculo> lista;
        
        // Lógica de búsqueda: si hay palabra clave, filtra; si no, trae todo.
        if (palabraClave != null && !palabraClave.isEmpty()) {
            lista = vehiculoService.buscarPorModelo(palabraClave);
        } else {
            lista = vehiculoService.listarTodos();
        }

        model.addAttribute("listaVehiculos", lista);
        model.addAttribute("palabraClave", palabraClave); // Para mantener el texto en el input del buscador
        model.addAttribute("vehiculo", new Vehiculo());   // Objeto vacío para el formulario de creación
        return "index";
    }

    /**
     * Procesa la solicitud POST para guardar o actualizar un vehículo.
     *
     * @param vehiculo  El objeto {@link Vehiculo} poblado con los datos del formulario.
     * @param attribute Objeto para añadir mensajes "flash" (alertas) que sobreviven a la redirección.
     * @return Redirección a la página principal.
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("vehiculo") Vehiculo vehiculo, RedirectAttributes attribute) {
        vehiculoService.guardar(vehiculo);
        attribute.addFlashAttribute("success", "¡Vehículo guardado correctamente!");
        return "redirect:/";
    }

    /**
     * Prepara el formulario para la edición de un vehículo existente.
     * Busca el vehículo por ID y lo carga en el modelo para que el formulario aparezca lleno.
     *
     * @param id    El identificador único del vehículo a editar (extraído de la URL).
     * @param model Modelo para enviar los datos a la vista.
     * @return La vista "index" en modo edición o redirección con error si falla.
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        try {
            Vehiculo vehiculo = vehiculoService.obtenerPorId(id);
            // Necesitamos recargar la lista para que la tabla de fondo no desaparezca al editar
            model.addAttribute("listaVehiculos", vehiculoService.listarTodos());
            model.addAttribute("vehiculo", vehiculo);
            model.addAttribute("editMode", true); // Bandera para cambiar el texto del botón a "Actualizar"
            return "index";
        } catch (Exception e) {
            return "redirect:/?error=NoEncontrado";
        }
    }

    /**
     * Elimina un registro individual del sistema.
     *
     * @param id        El identificador del vehículo a eliminar.
     * @param attribute Objeto para enviar mensajes de éxito o error al usuario.
     * @return Redirección a la página principal.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes attribute) {
        try {
            vehiculoService.eliminar(id);
            attribute.addFlashAttribute("warning", "Registro eliminado exitosamente.");
        } catch (Exception e) {
            attribute.addFlashAttribute("error", "Error al intentar eliminar el vehículo.");
        }
        return "redirect:/";
    }

    /**
     * Procesa la eliminación masiva de vehículos seleccionados mediante checkboxes.
     *
     * @param ids       Lista de identificadores (Long) recibidos desde la vista.
     * @param attribute Objeto para enviar confirmación de la cantidad de registros borrados.
     * @return Redirección a la página principal.
     */
    @PostMapping("/eliminar-multiples")
    public String eliminarMultiples(@RequestParam(value = "ids", required = false) List<Long> ids, RedirectAttributes attribute) {
        if (ids != null && !ids.isEmpty()) {
            vehiculoService.eliminarMultiples(ids);
            attribute.addFlashAttribute("warning", "Se han eliminado " + ids.size() + " registros.");
        }
        return "redirect:/";
    }
}