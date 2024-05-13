package DaoTest;

import Dao.Implementacion.PacienteDaoImpl;
import Dao.Interfaces.PacienteDAO;
import Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PacienteDaoTest {
    private PacienteDAO pacienteDao;

    @BeforeEach
    public void setUp() {
        pacienteDao = new PacienteDaoImpl();
    }
    ObraSocial osde = new ObraSocial(1,"OSDE");
    ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
    ObraSocial swissMedical = new ObraSocial(3,"Swiss Medical");
    ObraSocial galeno = new ObraSocial(4,"Galeno");

    @Test
    public void registrarYbucarTodosTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        Paciente paciente2 = new Paciente(2,"Pedro","Gomez",sanCorSalud);
        Paciente paciente3 = new Paciente(3,"Maria","Lopez",swissMedical);
        pacienteDao.registrar(paciente1);
        pacienteDao.registrar(paciente2);
        pacienteDao.registrar(paciente3);

        assertEquals(paciente1, pacienteDao.buscarPorId(1));
        assertEquals(paciente2, pacienteDao.buscarPorId(2));
        assertEquals(paciente3, pacienteDao.buscarPorId(3));
    }

    @Test
    public void agregarPacienteExistenteTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        pacienteDao.registrar(paciente1);
        try {
            pacienteDao.registrar(paciente1);
        } catch (IllegalArgumentException e) {
            assertEquals("El medico con id 1 ya existe.", e.getMessage());
        }
    }

    @Test
    public void buscarPorIdTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        pacienteDao.registrar(paciente1);
        assertEquals(paciente1, pacienteDao.buscarPorId(1));

    }

    @Test
    public void buscarPorNombreExisteTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        pacienteDao.registrar(paciente1);
        assertEquals(paciente1, pacienteDao.buscarPorNombre("Juan").get(0));

    }

    @Test
    public void buscarPorNombreNoExisteTest() {
        try {
            pacienteDao.buscarPorNombre("Juan");
        } catch (NoSuchElementException e) {
            assertEquals("No existe Juan.", e.getMessage());
        }
    }

    @Test
    public void buscarPorApellidoExisteTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        pacienteDao.registrar(paciente1);
        assertEquals(paciente1, pacienteDao.buscarPorApellido("Perez").get(0));

    }

    @Test
    public void buscarPorApellidoNoExisteTest() {
        try {
            pacienteDao.buscarPorApellido("Perez");
        } catch (NoSuchElementException e) {
            assertEquals("No existe Perez.", e.getMessage());
        }
    }

    @Test
    public void buscarPorObraSocialExisteTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        pacienteDao.registrar(paciente1);
        assertEquals(paciente1, pacienteDao.buscarPorObraSocial(osde).get(0));

    }

    @Test
    public void buscarPorObraSocialNoExisteTest() {
        try {
            pacienteDao.buscarPorObraSocial(osde);
        } catch (NoSuchElementException e) {
            assertEquals("No existe OSDE.", e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        Paciente paciente2 = new Paciente(2,"Pedro","Gomez",sanCorSalud);
        Paciente paciente3 = new Paciente(3,"Maria","Lopez",swissMedical);
        pacienteDao.registrar(paciente1);
        pacienteDao.registrar(paciente2);
        pacienteDao.registrar(paciente3);

        pacienteDao.elimnar(2);

        assertNull(pacienteDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        Paciente paciente2 = new Paciente(2,"Pedro","Gomez",sanCorSalud);
        Paciente paciente3 = new Paciente(3,"Maria","Lopez",swissMedical);
        pacienteDao.registrar(paciente1);
        pacienteDao.registrar(paciente2);
        pacienteDao.registrar(paciente3);

        Paciente pacienteModificado = new Paciente(2,"Pedro","Gomez",galeno);

        pacienteDao.modificar(pacienteModificado);

        assertEquals(pacienteModificado, pacienteDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        Paciente pacienteModificado = new Paciente(2,"Pedro","Gomez",galeno);
        try {
            pacienteDao.modificar(pacienteModificado);
        } catch (IllegalArgumentException e) {
            assertEquals("El paciente con id 2 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
        Paciente paciente2 = new Paciente(2,"Pedro","Gomez",sanCorSalud);
        Paciente paciente3 = new Paciente(3,"Maria","Lopez",swissMedical);
        pacienteDao.registrar(paciente1);
        pacienteDao.registrar(paciente2);
        pacienteDao.registrar(paciente3);

        assertEquals(paciente1, pacienteDao.buscarPorId(1));
        assertEquals(paciente2, pacienteDao.buscarPorId(2));
        assertEquals(paciente3, pacienteDao.buscarPorId(3));
        assertEquals(3, pacienteDao.listarTodos().size());
    }
}
