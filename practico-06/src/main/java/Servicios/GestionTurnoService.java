package Servicios;

import Entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionTurnoService {
    private static GestionTurnoService instancia;
    private ContenedorMemoria contenedorMemoria;

    private GestionTurnoService(ContenedorMemoria contenedorMemoria) {
        this.contenedorMemoria = contenedorMemoria;
    }

    public static GestionTurnoService getInstancia(ContenedorMemoria contenedorMemoria) {
        if (instancia == null) {
            instancia = new GestionTurnoService(contenedorMemoria);
        }
        return instancia;
    }

    public Especialidad listarEspecialidades() {
        List<Especialidad> especialidades = contenedorMemoria.getEspecialidadDao().listarTodos();

        if (especialidades.isEmpty()) {
            throw new RuntimeException("No hay especialidades disponibles");
        } else {
            for (int i = 0; i < especialidades.size(); i++) {
                System.out.println((i + 1) + " - " + especialidades.get(i).getNombre());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el número de la especialidad seleccionada:");
            int seleccion = scanner.nextInt();

            if (seleccion < 1 || seleccion > especialidades.size()) {
                throw new IllegalArgumentException("Número de especialidad inválido");
            } else {
                return especialidades.get(seleccion - 1);
            }
        }
    }

    public Medico listarMedicosPorEspecialidad(Especialidad especialidad, Paciente paciente, Boolean particular) {
        List<Medico> medicos = contenedorMemoria.getMedicoDao().buscarPorEspecialidad(especialidad);
        List<Medico> medicosFiltrados = new ArrayList<>();

        try {
            if (particular) {
                medicosFiltrados = medicos;
            } else {
                for (Medico medico : medicos) {
                    if (medico.getObrasSocialesAceptadas().contains(paciente.getObraSocial())) {
                        medicosFiltrados.add(medico);
                    }
                }
            }

            for (int i = 0; i < medicosFiltrados.size(); i++) {
                System.out.println((i + 1) + " - " + medicosFiltrados.get(i).getNombre() + " " + medicosFiltrados.get(i).getApellido());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Seleccione un médico: ");
            System.out.println("-Ingrese el número del médico-");
            int seleccion = scanner.nextInt();

            if (seleccion < 1 || seleccion > medicosFiltrados.size()) {
                throw new IllegalArgumentException("Número de especialidad inválido");
            } else {
                return medicosFiltrados.get(seleccion - 1);
            }
        }
        catch (Exception e) {
            System.out.println("No hay médicos disponibles para la especialidad seleccionada");
        }
        return null;
    }

    public void darTurnoAPaciente(Paciente paciente, Medico medico, Boolean particular) {
        int cantidadTurnos = contenedorMemoria.getTurnoDao().listarTodos().size();

        Turno turnoAAsignar = new Turno(cantidadTurnos,paciente, medico, particular, "Pendiente");

        contenedorMemoria.getTurnoDao().registrar(turnoAAsignar);
    }
}
