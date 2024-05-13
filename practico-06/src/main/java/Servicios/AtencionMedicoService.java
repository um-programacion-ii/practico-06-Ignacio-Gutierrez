package Servicios;

import Entidades.*;

import java.util.List;

public class AtencionMedicoService {
    private static AtencionMedicoService instancia;
    private ContenedorMemoria contenedorMemoria;

    private AtencionMedicoService(ContenedorMemoria contenedorMemoria) {
        this.contenedorMemoria = contenedorMemoria;
    }

    public static AtencionMedicoService getInstancia(ContenedorMemoria contenedorMemoria) {
        if (instancia == null) {
            instancia = new AtencionMedicoService(contenedorMemoria);
        }
        return instancia;
    }

    public Turno iniciarConsulta(Turno turno) {
        System.out.println("Iniciando consulta...");
        Turno turnoActualizado = new Turno(turno.getId(), turno.getPaciente(), turno.getMedico(), turno.getParticular(), "En curso");
        contenedorMemoria.getTurnoDao().modificar(turnoActualizado);
        return turnoActualizado;
    }

    public Receta generarReceta(Turno turno, List<Medicamento> medicamentos) {
        if (!medicamentos.isEmpty()) {
            System.out.println("Generando receta...");

            int numeroDeRecetas = contenedorMemoria.getRecetaDao().listarTodos().size();

            Receta recetaGenerada = new Receta(numeroDeRecetas + 1, medicamentos, turno.getMedico(), turno.getPaciente());

            contenedorMemoria.getRecetaDao().registrar(recetaGenerada);

            return recetaGenerada;
        }
        return null;
    }

    public void finalizarConsulta(Turno turno) {
        Turno turnoActualizado = new Turno(turno.getId(), turno.getPaciente(), turno.getMedico(), turno.getParticular(), "Finalizada");
        contenedorMemoria.getTurnoDao().modificar(turnoActualizado);
        System.out.println("Consulta finalizada...");
    }
}