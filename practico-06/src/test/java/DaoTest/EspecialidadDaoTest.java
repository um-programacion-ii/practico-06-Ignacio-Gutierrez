package DaoTest;

import Dao.EspecialidadDAO;
import Entidades.Especialidad;
import Entidades.EspecialidadDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class EspecialidadDaoTest {
    private EspecialidadDAO especialidadDao;

    @BeforeEach
    public void setUp() {
        especialidadDao = new EspecialidadDaoImpl();
    }

    @Test
    public void registrarYbucarTodosTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");
        Especialidad especialidad3 = new Especialidad(3,"Oftalmología");
        Especialidad especialidad4 = new Especialidad(4,"Cardiología");
        especialidadDao.registrar(especialidad1);
        especialidadDao.registrar(especialidad2);
        especialidadDao.registrar(especialidad3);
        especialidadDao.registrar(especialidad4);

        assertEquals(especialidad1, especialidadDao.buscarPorId(1));
        assertEquals(especialidad2, especialidadDao.buscarPorId(2));
        assertEquals(especialidad3, especialidadDao.buscarPorId(3));
        assertEquals(especialidad4, especialidadDao.buscarPorId(4));
    }

    @Test
    public void agregarEspecialidadExistenteTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        especialidadDao.registrar(especialidad1);
        try {
            especialidadDao.registrar(especialidad1);
        } catch (IllegalArgumentException e) {
            assertEquals("La especialidad con id 1 ya existe.", e.getMessage());
        }
    }

    @Test
    public void buscarPorIdTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        especialidadDao.registrar(especialidad1);
        assertEquals(especialidad1, especialidadDao.buscarPorId(1));

    }

    @Test
    public void buscarPorNombreExisteTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        especialidadDao.registrar(especialidad1);
        assertEquals(especialidad1, especialidadDao.buscarPorNombre("Dermatologia"));

    }

    @Test
    public void buscarPorNombreNoExisteTest() {
        try {
            Especialidad especialidad = especialidadDao.buscarPorNombre("Dermatologia");
        } catch (NoSuchElementException e) {
            assertEquals("No hay Dermatologia.", e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");
        Especialidad especialidad3 = new Especialidad(3,"Oftalmología");
        especialidadDao.registrar(especialidad1);
        especialidadDao.registrar(especialidad2);
        especialidadDao.registrar(especialidad3);

        especialidadDao.elimnar(2);

        assertNull(especialidadDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");
        Especialidad especialidad3 = new Especialidad(3,"Oftalmología");
        especialidadDao.registrar(especialidad1);
        especialidadDao.registrar(especialidad2);
        especialidadDao.registrar(especialidad3);

        Especialidad especialidadModificada = new Especialidad(2,"Neurología");
        especialidadDao.modificar(especialidadModificada);

        assertEquals(especialidadModificada, especialidadDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        Especialidad especialidadModificada = new Especialidad(2,"Neurología");
        try {
            especialidadDao.modificar(especialidadModificada);
        } catch (IllegalArgumentException e) {
            assertEquals("La especialidad con id 2 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");
        Especialidad especialidad3 = new Especialidad(3,"Oftalmología");
        especialidadDao.registrar(especialidad1);
        especialidadDao.registrar(especialidad2);
        especialidadDao.registrar(especialidad3);

        assertEquals(especialidad1, especialidadDao.buscarPorId(1));
        assertEquals(especialidad2, especialidadDao.buscarPorId(2));
        assertEquals(especialidad3, especialidadDao.buscarPorId(3));
        assertEquals(3, especialidadDao.listarTodos().size());
    }
}