package DaoTest;

import Entidades.Drogueria;

import Dao.Implementacion.MedicamentoDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class DrogueriaTest {
    private Drogueria drogueria;
    private MedicamentoDaoImpl medicamentoDaoImpl;

    @BeforeEach
    public void setUp() {
        medicamentoDaoImpl = Mockito.mock(MedicamentoDaoImpl.class);
        drogueria = new Drogueria(medicamentoDaoImpl);
    }

    @Test
    public void solicitarMedicamentoPorIdTest() {
        drogueria.solicitarMedicamentoPorId(1);
        verify(medicamentoDaoImpl).agregarCantidadPorId(1, 500);
    }

    @Test
    public void solicitarMedicamentoPorNombreTest() {
        drogueria.solicitarMedicamentoPorNombre("Paracetamol");
        verify(medicamentoDaoImpl).agregarCantidadPorNombre("Paracetamol", 500);
    }
}