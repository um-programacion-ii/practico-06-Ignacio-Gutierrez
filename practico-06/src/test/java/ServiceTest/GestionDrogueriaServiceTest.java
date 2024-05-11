package ServiceTest;

import Dao.Interfaces.MedicamentoDAO;
import Entidades.ContenedorMemoria;
import Servicios.GestionDrogueriaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertSame;

public class GestionDrogueriaServiceTest {
    private GestionDrogueriaService gestionDrogueriaService;
    private ContenedorMemoria contenedorMemoria;

    @BeforeEach
    public void setUp() {
        contenedorMemoria = Mockito.mock(ContenedorMemoria.class);
        MedicamentoDAO medicamentoDAO = Mockito.mock(MedicamentoDAO.class);
        Mockito.when(contenedorMemoria.getMedicamentoDao()).thenReturn(medicamentoDAO);
        gestionDrogueriaService = GestionDrogueriaService.getInstancia(contenedorMemoria);
    }

    @Test
    public void solicitarMedicamentoPorIdTest() {
        gestionDrogueriaService.solicitarMedicamentoPorId(1);
        Mockito.verify(contenedorMemoria.getMedicamentoDao()).agregarCantidadPorId(1, 500);
    }

    @Test
    void getInstanciaTest() {
        GestionDrogueriaService instancia1 = GestionDrogueriaService.getInstancia(contenedorMemoria);
        GestionDrogueriaService instancia2 = GestionDrogueriaService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }
}
