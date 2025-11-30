/**
 * scripts.js
 * Funcionalidad para la gestión de vehículos
 */

// Función para seleccionar/deseleccionar todos los checkboxes
function toggleAll(source) {
    var checkboxes = document.getElementsByName('ids');
    for(var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = source.checked;
    }
}

// Validación antes de enviar el borrado masivo
function confirmarBorradoMasivo() {
    var checkboxes = document.getElementsByName('ids');
    var seleccionado = false;
    
    // Verificar si hay al menos uno seleccionado
    for(var i = 0; i < checkboxes.length; i++) {
        if(checkboxes[i].checked) {
            seleccionado = true;
            break;
        }
    }

    if(!seleccionado) {
        alert("⚠️ Por favor, selecciona al menos un registro para borrar.");
        return false; // Cancela el envío del formulario
    }

    return confirm('¿Estás seguro de eliminar TODOS los registros seleccionados?\n\nEsta acción no se puede deshacer.');
}

// Validación del formulario (Cliente)
function validarFormulario() {
    var precioInput = document.getElementById('precio');
    var modeloInput = document.getElementById('modelo');
    
    // Validar precio negativo
    if(precioInput.value < 0) {
        alert("⛔ El precio no puede ser negativo.");
        precioInput.focus();
        return false;
    }

    // Validar modelo vacío (aunque tiene 'required' en HTML, es doble seguridad)
    if(modeloInput.value.trim() === "") {
        alert("⛔ El modelo es obligatorio.");
        modeloInput.focus();
        return false;
    }

    return true;
}