package Entidades;

import Dao.Interfaces.*;
import Servicios.*;

import java.util.*;

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
            System.out.println("\nIngrese 'Si' para iniciar la consulta u 'Otro' para finalizar el programa");
            String respuesta1 = scanner.nextLine();
            if (respuesta1.equals("Si")) {
                Turno turnoActualizado = atencionMedicoService.iniciarConsulta(turnoDelUsuario);

                Random random = new Random();
                Boolean generarReceta = random.nextBoolean();
                if (generarReceta) {

                    //Lista de medicamentos para la receta
                    List<Medicamento> todosLosMedicamentos = contenedorMemoria.getMedicamentoDao().listarTodos();

                    List<Medicamento> medicamentosReceta = new ArrayList<>();
                    for (Medicamento medicamento : todosLosMedicamentos) {
                        int cantidad = random.nextInt(5) + 1;
                        medicamentosReceta.add(new Medicamento(medicamento.getId(), medicamento.getNombre(), cantidad));
                    }

                    int cantidadMedicamentos = random.nextInt(2) + 1;
                    List<Medicamento> medicamentosSeleccionados = new ArrayList<>();

                    for (int i = 0; i < cantidadMedicamentos; i++) {
                        if (!medicamentosReceta.isEmpty()) {
                            int indiceAleatorio = random.nextInt(medicamentosReceta.size());
                            Medicamento medicamentoSeleccionado = medicamentosReceta.get(indiceAleatorio);
                            medicamentosSeleccionados.add(medicamentoSeleccionado);
                            medicamentosReceta.remove(indiceAleatorio);
                        }
                    }
                    Receta recetaGenerada = atencionMedicoService.generarReceta(turnoActualizado, medicamentosSeleccionados);
                    System.out.println("Receta generada exitosamente!");
                    System.out.println("Recuerde el id de su receta, para comprar los medicamentos:" + recetaGenerada.getId());
                    System.out.println(recetaGenerada);
                } else {
                    System.out.println("No se generará receta");
                }

                atencionMedicoService.finalizarConsulta(turnoActualizado);
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// Turno de Paciente ///////////////////////////////////////////////

            System.out.println("\nIngrese 'Si' para comprar medicamentos u 'Otro' para finalizar el programa");
            String respuesta2 = scanner.nextLine();
            if (respuesta2.equals("Si")) {
                try {
                    System.out.println("Ingrese el id de su Receta: ");
                    String idRecetaPacienteString = scanner.nextLine();
                    int idRecetaPaciente = Integer.parseInt(idRecetaPacienteString);

                    Receta recetaGenerada = recetaDAO.buscarPorId(idRecetaPaciente);
                    Turno turnoFinalizado = turnoDAO.buscarPorId(turnoDelUsuario.getId());

                    gestionFarmaciaService.compraDeMedicamentos(recetaGenerada, turnoFinalizado);

                } catch (Exception e) {
                } finally {
                    for (Medicamento medicamento : contenedorMemoria.getMedicamentoDao().listarTodos()) {
                        System.out.println("Id: " + medicamento.getId() + " - Medicamento: " + medicamento.getNombre() + " - Cantidad: " + medicamento.getCantidad());
                    }
                }
            }

            System.out.println("\nIngrese 'Si' para seguir en el sistema u 'Otro' para finalizar el programa");

            String respuesta3 = scanner.nextLine();
            if (respuesta3.equals("Si")) {
                estadoPrograma = "Activo";
            } else {
                estadoPrograma = "Exit";
            }
        }
    }

    public void finalizar() {
        System.out.println("\n|-------------------------------------------------|");
        System.out.println("|   Finalizó su estadía en el Servicio de Salud!  |");
        System.out.println("|-------------------------------------------------|");
    }

}
