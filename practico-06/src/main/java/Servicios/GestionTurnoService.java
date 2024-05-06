package Servicios;

import Entidades.*;

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
    

    public void darTurnoAPaciente(Paciente paciente, Medico medico, Boolean particular) {
        int cantidadTurnos = contenedorMemoria.getTurnoDao().listarTodos().size();

        Turno turnoAAsignar = new Turno(cantidadTurnos,paciente, medico, particular, "Pendiente");

        contenedorMemoria.getTurnoDao().registrar(turnoAAsignar);
    }
}
