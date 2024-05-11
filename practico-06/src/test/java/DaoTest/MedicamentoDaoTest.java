package DaoTest;

import Dao.Interfaces.MedicamentoDAO;
import Entidades.Medicamento;
import Dao.Implementacion.MedicamentoDaoImpl;
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
            medicamentoDao.buscarPorNombre("Paracetamol");
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
            assertEquals("El medicamento con id 4 no existe.", e.getMessage());
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


    @Test
    public void retirarCantidadPorIdTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.retirarCantidadPorId(1, 5);

        assertEquals(5, medicamentoDao.buscarPorId(1).getCantidad());
    }

    @Test
    public void retirarCantidadPorIdCantidadARetirarMayorTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        try {
            medicamentoDao.retirarCantidadPorId(1, 15);
        } catch (IllegalArgumentException e) {
            assertEquals("La cantidad a retirar es mayor que la cantidad disponible.", e.getMessage());

        }
    }

    @Test
    public void retirarCantidadPorIdNoExisteTest() {
        try {
            medicamentoDao.retirarCantidadPorId(1, 5);
        } catch (NoSuchElementException e) {
            assertEquals("No se encontró ningún medicamento con el ID especificado.", e.getMessage());
        }
    }


    @Test
    public void retirarCantidadPorNombreTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.retirarCantidadPorNombre("Paracetamol", 5);

        assertEquals(5, medicamentoDao.buscarPorId(1).getCantidad());
    }

    @Test
    public void retirarCantidadPorNombreCantidadARetirarMayorTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        try {
            medicamentoDao.retirarCantidadPorNombre("Paracetamol",15);
        } catch (IllegalArgumentException e) {
            assertEquals("La cantidad a retirar es mayor que la cantidad disponible.", e.getMessage());

        }
    }

    @Test
    public void retirarCantidadPorNombreNoExisteTest() {
        try {
            medicamentoDao.retirarCantidadPorNombre("Paracetamol", 5);
        } catch (NoSuchElementException e) {
            assertEquals("No hay Paracetamol.", e.getMessage());
        }
    }

    @Test
    public void agregarCantidadPorIdTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.agregarCantidadPorId(1, 5);

        assertEquals(15, medicamentoDao.buscarPorId(1).getCantidad());
    }

    @Test
    public void agregarCantidadPorIdNoExisteTest() {
        try {
            medicamentoDao.agregarCantidadPorId(1, 5);
        } catch (NoSuchElementException e) {
            assertEquals("No se encontró ningún medicamento con el ID especificado.", e.getMessage());
        }
    }


    @Test
    public void agregarCantidadPorNombreTest() {
        Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
        medicamentoDao.registrar(medicamento1);
        medicamentoDao.agregarCantidadPorNombre("Paracetamol", 5);

        assertEquals(15, medicamentoDao.buscarPorId(1).getCantidad());
    }

    @Test
    public void agregarCantidadPorNombreNoExisteTest() {
        try {
            medicamentoDao.agregarCantidadPorNombre("Paracetamol", 5);
        } catch (NoSuchElementException e) {
            assertEquals("No hay Paracetamol.", e.getMessage());
        }
    }

}
