package Servicios;

import Dao.Implementacion.PacienteDaoImpl;
import Entidades.ContenedorMemoria;
import Entidades.Paciente;

import java.util.List;
import java.util.NoSuchElementException;

public class RegistroDePacientesService {
    private ContenedorMemoria contenedorMemoria;

    public RegistroDePacientesService(ContenedorMemoria contenedorMemoria) {
        this.contenedorMemoria = contenedorMemoria;
    }

    public Paciente buscarPacientePorNombreYApellido(String nombre, String apellido) {
        try {
            List<Paciente> pacienteNombre = contenedorMemoria.getPacienteDao().buscarPorNombre(nombre);
            List<Paciente> pacienteApellido = contenedorMemoria.getPacienteDao().buscarPorApellido(apellido);

            if (!pacienteNombre.isEmpty() && !pacienteApellido.isEmpty()) {
                for (Paciente paciente : pacienteNombre) {
                    if (pacienteApellido.contains(paciente)) {
                        return paciente;
                    }
                }
                throw new NoSuchElementException(apellido + ", " + nombre + " no se encuentra registrado en el sistema.");

            } else {
                throw new NoSuchElementException(apellido + ", " + nombre + " no se encuentra registrado en el sistema.");
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(apellido + ", " + nombre + " no se encuentra registrado en el sistema.");
        }
    }

    public void registrarPaciente(Paciente pacienteNuevo) {
        contenedorMemoria.getPacienteDao().registrar(pacienteNuevo);
    }
}
