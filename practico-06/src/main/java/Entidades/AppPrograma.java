package Entidades;

import Dao.Interfaces.*;
import Servicios.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppPrograma {
    public void iniciar() {
        System.out.println("\n|----------------------------------------|");
        System.out.println("|   Bienvendido/a al Servicio de Salud!  |");
        System.out.println("|----------------------------------------|");
    }

    public void logicaDelPrograma () {
        ContenedorMemoria contenedorMemoria = ContenedorMemoria.getInstance();
        EspecialidadDAO especialidadDAO = contenedorMemoria.getEspecialidadDao();
        MedicamentoDAO medicamentoDAO = contenedorMemoria.getMedicamentoDao();
        ObraSocialDAO obraSocialDAO = contenedorMemoria.getObraSocialDao();
        MedicoDAO medicoDAO = contenedorMemoria.getMedicoDao();
        PacienteDAO pacienteDAO = contenedorMemoria.getPacienteDao();
        TurnoDAO turnoDAO = contenedorMemoria.getTurnoDao();
        RecetaDAO recetaDAO = contenedorMemoria.getRecetaDao();

        RegistroDePacientesService registroDePacientesService = RegistroDePacientesService.getInstancia(contenedorMemoria);
        GestionTurnoService gestionTurnoService = GestionTurnoService.getInstancia(contenedorMemoria);
        AtencionMedicoService atencionMedicoService = AtencionMedicoService.getInstancia(contenedorMemoria);
        GestionFarmaciaService gestionFarmaciaService = GestionFarmaciaService.getInstancia(contenedorMemoria);
        GestionDrogueriaService gestionDrogueriaService = GestionDrogueriaService.getInstancia(contenedorMemoria);

        String estadoPrograma = "Activo";

        while (estadoPrograma != "Exit") {
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////// Registro De Pacientes /////////////////////////////////////////////
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese su apellido: ");
            String apellido = scanner.nextLine();

            try {
                Paciente pacienteUsuario = registroDePacientesService.buscarPacientePorNombreYApellido(nombre, apellido);
                System.out.println("Paciente encontrado: " + pacienteUsuario.getNombre() + " " + pacienteUsuario.getApellido());
            } catch (NoSuchElementException e) {
                System.out.println("Paciente no encontrado");
                System.out.println("Lo registraremos en el sistema...\n");
                ObraSocial obraSocialDelPaciente = registroDePacientesService.seleccionarObraSocial();

                int idPaciente = pacienteDAO.listarTodos().size() + 1;

                Paciente pacienteUsuario = new Paciente(idPaciente, nombre, apellido, obraSocialDelPaciente);
                registroDePacientesService.registrarPaciente(pacienteUsuario);

                System.out.println("Paciente registrado exitosamente!");
            }


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// Turno de Paciente ///////////////////////////////////////////////
            System.out.println("\nSeleccione la especialidad de la consulta:");
            Especialidad especialidadSeleccionada = gestionTurnoService.listarEspecialidades();

            Boolean particularSiONo = gestionTurnoService.seleccionarTipoTurno();

            Paciente pacienteUsuario = registroDePacientesService.buscarPacientePorNombreYApellido(nombre, apellido);

            Medico medicoSeleccionada = gestionTurnoService.listarMedicosPorEspecialidad(especialidadSeleccionada, pacienteUsuario, particularSiONo);

            Turno turnoDelUsuario = gestionTurnoService.darTurnoAPaciente(pacienteUsuario, medicoSeleccionada, particularSiONo);

            System.out.println(turnoDelUsuario);
            System.out.println("Turno asignado exitosamente!");


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// Turno de Paciente ///////////////////////////////////////////////


        }
        ////////OJALA LO PUEDA TERMINAR
    }
}
