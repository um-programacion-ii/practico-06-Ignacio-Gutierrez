package Servicios;

import Entidades.*;

import java.util.List;


public class GestionFarmaciaService {
    private static GestionFarmaciaService instancia;
    private ContenedorMemoria contenedorMemoria;
    private GestionDrogueriaService gestionDrogueriaService;

    private GestionFarmaciaService(ContenedorMemoria contenedorMemoria) {
        this.contenedorMemoria = contenedorMemoria;
    }

    public static GestionFarmaciaService getInstancia(ContenedorMemoria contenedorMemoria) {
        if (instancia == null) {
            instancia = new GestionFarmaciaService(contenedorMemoria);
            instancia.gestionDrogueriaService = GestionDrogueriaService.getInstancia(contenedorMemoria);
        }
        return instancia;
    }

    public void compraDeMedicamentos(Receta receta, Turno turno) {
        String estadoTurno = turno.getEstadoTurno();
        List<Medicamento> medicamentosRecetados = receta.getMedicamentos();
        if (estadoTurno == "Finalizada") {
            for(Medicamento medicamento : medicamentosRecetados) {
                int cantidadARetirar = medicamento.getCantidad();
                try {
                    contenedorMemoria.getMedicamentoDao().retirarCantidadPorId(medicamento.getId(), cantidadARetirar);
                } catch (Exception e) {
                    System.out.println("No hay suficiente stock de " + medicamento.getNombre());
                    System.out.println("Solicitando medicamento a droguería...");
                    gestionDrogueriaService.solicitarMedicamentoPorId(medicamento.getId());

                    contenedorMemoria.getMedicamentoDao().retirarCantidadPorId(medicamento.getId(), cantidadARetirar);
                } finally {
                    System.out.println("Medicamento retirado: " + medicamento.getNombre());
                }
            }
        } else {
            throw new RuntimeException("El turno no está finalizado");
        }

        System.out.println("Compra finalizada...");
    }

}
