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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su apellido: ");
        String apellido = scanner.nextLine();

        try {
            Paciente paciente = registroDePacientesService.buscarPacientePorNombreYApellido(nombre, apellido);
            System.out.println("Paciente encontrado: " + paciente.getNombre() + " " + paciente.getApellido());
        } catch (NoSuchElementException e) {
            System.out.println("Paciente no encontrado");
            System.out.println("Lo registraremos en el sistema...\n");
            ObraSocial obraSocialDelPaciente = registroDePacientesService.seleccionarObraSocial();

            int idPaciente = pacienteDAO.listarTodos().size() + 1;

            Paciente pacienteNuevo = new Paciente(idPaciente, nombre, apellido, obraSocialDelPaciente);
            registroDePacientesService.registrarPaciente(pacienteNuevo);

            System.out.println("Paciente registrado exitosamente!");
        }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }
    ////////OJALA LO PUEDA TERMINAR
    public void finalizar() {
        System.out.println("\n|----------------------------------------|");
        System.out.println("| Gracias por usar el Servicio de Salud! |");
        System.out.println("|----------------------------------------|");
    }
}
