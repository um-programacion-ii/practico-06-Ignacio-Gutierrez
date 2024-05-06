package Servicios;

import Entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionTurnoService {
    private static GestionTurnoService instancia;
    private ContenedorMemoria contenedorMemoria;

    public GestionTurnoService(ContenedorMemoria contenedorMemoria) {
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
        for (int i = 0; i < especialidades.size(); i++) {
            System.out.println((i + 1) + " - " + especialidades.get(i).getNombre());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione una especialidad: ");
        System.out.println("-Ingrese el número de la especialidad-");
        int seleccion = scanner.nextInt();

        return especialidades.get(seleccion - 1);
    }

    public Medico listarMedicosPorEspecialidad(Especialidad especialidad, Paciente paciente, Boolean particular) {
        List<Medico> medicos = contenedorMemoria.getMedicoDao().buscarPorEspecialidad(especialidad);
        List<Medico> medicosFiltrados = new ArrayList<>();

        if (particular) {
            medicosFiltrados = medicos;
        } else {
            for (Medico medico : medicos) {
                if (medico.getObrasSocialesAceptadas().contains(paciente.getObraSocial())) {
                    medicosFiltrados.add(medico);
                }
            }
        }

        if (medicosFiltrados.isEmpty()) {
            throw new RuntimeException("No hay médicos disponibles para la especialidad seleccionada");
        }

        for (int i = 0; i < medicosFiltrados.size(); i++) {
            System.out.println((i + 1) + " - " + medicosFiltrados.get(i).getNombre());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione un médico: ");
        System.out.println("-Ingrese el número del médico-");
        int seleccion = scanner.nextInt();

        return medicosFiltrados.get(seleccion - 1);
    }

    public void darTurnoAPaciente(Paciente paciente, Medico medico, Boolean particular) {
        int cantidadTurnos = contenedorMemoria.getTurnoDao().listarTodos().size();

        Turno turnoAAsignar = new Turno(cantidadTurnos,paciente, medico, particular, "Pendiente");

        contenedorMemoria.getTurnoDao().registrar(turnoAAsignar);
    }
}
