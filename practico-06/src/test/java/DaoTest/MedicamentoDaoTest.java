package DaoTest;

import Dao.MedicamentoDAO;
import Entidades.Medicamento;
import Entidades.MedicamentoDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MedicamentoDaoTest {
    private MedicamentoDAO medicamentoDao;

    @BeforeEach
    public void setUp() {
        medicamentoDao = new MedicamentoDaoImpl();
    }

    @Test
    public void registrarYbucarTodosTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        Medicamento medicamento2 = new Medicamento(2,"Ibuprofeno", 20);
        Medicamento medicamento3 = new Medicamento(3,"Omeprazol", 30);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.registrar(medicamento2);
        medicamentoDao.registrar(medicamento3);

        assertEquals(medicamento1, medicamentoDao.buscarPorId(1));
        assertEquals(medicamento2, medicamentoDao.buscarPorId(2));
        assertEquals(medicamento3, medicamentoDao.buscarPorId(3));
    }

    @Test
    public void agregarMedicamentoExistenteTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        try {
            medicamentoDao.registrar(medicamento1);
        } catch (IllegalArgumentException e) {
            assertEquals("El medicamento con id 1 ya existe.", e.getMessage());
        }
    }

    @Test
    public void buscarPorIdTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        assertEquals(medicamento1, medicamentoDao.buscarPorId(1));

    }

    @Test
    public void buscarPorNombreExisteTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        assertEquals(medicamento1, medicamentoDao.buscarPorNombre("Paracetamol"));

    }

    @Test
    public void buscarPorNombreNoExisteTest() {
        try {
            Medicamento medicamento = medicamentoDao.buscarPorNombre("Paracetamol");
        } catch (NoSuchElementException e) {
            assertEquals("No hay Paracetamol.", e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        Medicamento medicamento2 = new Medicamento(2,"Ibuprofeno", 20);
        Medicamento medicamento3 = new Medicamento(3,"Omeprazol", 30);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.registrar(medicamento2);
        medicamentoDao.registrar(medicamento3);

        medicamentoDao.elimnar(2);

        assertNull(medicamentoDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        Medicamento medicamento2 = new Medicamento(2,"Ibuprofeno", 20);
        Medicamento medicamento3 = new Medicamento(3,"Omeprazol", 30);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.registrar(medicamento2);
        medicamentoDao.registrar(medicamento3);

        Medicamento medicamentoModificado = new Medicamento(2,"Ibuprofeno", 40);
        medicamentoDao.modificar(medicamentoModificado);

        assertEquals(medicamentoModificado, medicamentoDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        Medicamento medicamentoModificado = new Medicamento(4,"Ibuprofeno", 40);
        try {
            medicamentoDao.modificar(medicamentoModificado);
        } catch (IllegalArgumentException e) {
            assertEquals("El alumno con id 4 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        Medicamento medicamento2 = new Medicamento(2,"Ibuprofeno", 20);
        Medicamento medicamento3 = new Medicamento(3,"Omeprazol", 30);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.registrar(medicamento2);
        medicamentoDao.registrar(medicamento3);

        assertEquals(medicamento1, medicamentoDao.buscarPorId(1));
        assertEquals(medicamento2, medicamentoDao.buscarPorId(2));
        assertEquals(medicamento3, medicamentoDao.buscarPorId(3));
        assertEquals(3, medicamentoDao.listarTodos().size());
    }
}
