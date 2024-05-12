package Servicios;

import Entidades.ContenedorMemoria;
import Entidades.ObraSocial;
import Entidades.Paciente;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RegistroDePacientesService {
    private static RegistroDePacientesService instancia;
    private ContenedorMemoria contenedorMemoria;

    private RegistroDePacientesService(ContenedorMemoria contenedorMemoria) {
        this.contenedorMemoria = contenedorMemoria;
    }

    public static RegistroDePacientesService getInstancia(ContenedorMemoria contenedorMemoria) {
        if (instancia == null) {
            instancia = new RegistroDePacientesService(contenedorMemoria);
        }
        return instancia;
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
            }
            throw new NoSuchElementException(apellido + ", " + nombre + " no se encuentra registrado en el sistema.");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(apellido + ", " + nombre + " no se encuentra registrado en el sistema.");
        }
    }

    public ObraSocial seleccionarObraSocial() {
        List<ObraSocial> todasLasObrasSociales = contenedorMemoria.getObraSocialDao().listarTodos();

        if (todasLasObrasSociales.isEmpty()) {
            throw new IllegalStateException("No hay obras sociales disponibles.");
        }

        for (int i = 0; i < todasLasObrasSociales.size(); i++) {
            System.out.println((i + 1) + " - " + todasLasObrasSociales.get(i).getNombre());
        }

        Scanner scanner = new Scanner(System.in);
        int indiceSeleccionado = -1;

        while (indiceSeleccionado < 0 || indiceSeleccionado >= todasLasObrasSociales.size()) {
            System.out.println("Por favor, ingrese el número de la obra social que posee:");
            String input = scanner.nextLine();

            try {
                indiceSeleccionado = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }

        return todasLasObrasSociales.get(indiceSeleccionado);
    }

    public void registrarPaciente(Paciente pacienteNuevo) {
        contenedorMemoria.getPacienteDao().registrar(pacienteNuevo);
    }
}
