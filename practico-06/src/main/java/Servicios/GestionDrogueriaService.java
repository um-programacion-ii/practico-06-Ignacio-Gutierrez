package Servicios;

import Entidades.ContenedorMemoria;

public class GestionDrogueriaService {
    private static GestionDrogueriaService instancia;
    private ContenedorMemoria contenedorMemoria;

    private GestionDrogueriaService(ContenedorMemoria contenedorMemoria) {
        this.contenedorMemoria = contenedorMemoria;
    }

    public static GestionDrogueriaService getInstancia(ContenedorMemoria contenedorMemoria) {
        if (instancia == null) {
            instancia = new GestionDrogueriaService(contenedorMemoria);
        }
        return instancia;
    }

    public void solicitarMedicamentoPorId(int id) {
        contenedorMemoria.getMedicamentoDao().agregarCantidadPorId(id, 500);
    }
}
