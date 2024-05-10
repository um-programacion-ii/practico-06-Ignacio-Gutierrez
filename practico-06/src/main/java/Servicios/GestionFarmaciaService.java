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
        }
        return instancia;
    }

    public void compraDeMedicamentos(Receta receta, Turno turno) {
        String esadoTurno = turno.getEstadoTurno();
        List<Medicamento> medicamentos = receta.getMedicamentos();
        if (esadoTurno == "Finalizada") {
            for(Medicamento medicamento : medicamentos) {
                int cantidadARetirar = medicamento.getCantidad();
                try {
                    contenedorMemoria.getMedicamentoDao().retirarCantidadPorId(medicamento.getId(), cantidadARetirar);
                } catch (Exception e) {
                    System.out.println("No hay suficiente stock de " + medicamento.getNombre());
                    System.out.println("Solicitando medicamento a drogueria...");
                    gestionDrogueriaService.getInstancia(contenedorMemoria).solicitarMedicamentoPorId(medicamento.getId());

                    contenedorMemoria.getMedicamentoDao().retirarCantidadPorId(medicamento.getId(), cantidadARetirar);

                }
            }
            System.out.println("Compra de medicamentos realizada");
        } else {
            throw new RuntimeException("El turno no está finalizado");
        }

        System.out.println("Iniciando compra...");
    }

}
