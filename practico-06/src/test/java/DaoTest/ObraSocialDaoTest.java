package DaoTest;

import Dao.ObraSocialDAO;
import Entidades.ObraSocial;
import Entidades.ObraSocialDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ObraSocialDaoTest {
    private ObraSocialDAO obraSocialDao;

    @BeforeEach
    public void setUp() {
        obraSocialDao = new ObraSocialDaoImpl();
    }

    @Test
    public void registrarYbucarTodosTest() {
        ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        ObraSocial obraSocial2 = new ObraSocial(2,"SanCorSalud");
        ObraSocial obraSocial3 = new ObraSocial(3,"Swiss Medical");
        ObraSocial obraSocial4 = new ObraSocial(4,"Galeno");
        obraSocialDao.registrar(obraSocial1);
        obraSocialDao.registrar(obraSocial2);
        obraSocialDao.registrar(obraSocial3);
        obraSocialDao.registrar(obraSocial4);

        assertEquals(obraSocial1, obraSocialDao.buscarPorId(1));
        assertEquals(obraSocial2, obraSocialDao.buscarPorId(2));
        assertEquals(obraSocial3, obraSocialDao.buscarPorId(3));
        assertEquals(obraSocial4, obraSocialDao.buscarPorId(4));
    }

    @Test
    public void agregarEspecialidadExistenteTest() {
        ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        obraSocialDao.registrar(obraSocial1);
        try {
            obraSocialDao.registrar(obraSocial1);
        } catch (IllegalArgumentException e) {
            assertEquals("La obrasocial con id 1 ya existe.", e.getMessage());
        }
    }

    @Test
    public void buscarPorIdTest() {
        ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        obraSocialDao.registrar(obraSocial1);
        assertEquals(obraSocial1, obraSocialDao.buscarPorId(1));

    }

    @Test
    public void buscarPorNombreExisteTest() {
        ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        obraSocialDao.registrar(obraSocial1);
        assertEquals(obraSocial1, obraSocialDao.buscarPorNombre("OSDE"));

    }

    @Test
    public void buscarPorNombreNoExisteTest() {
        try {
            ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        } catch (NoSuchElementException e) {
            assertEquals("No hay OSDE   .", e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        ObraSocial obraSocial2 = new ObraSocial(2,"SanCorSalud");
        ObraSocial obraSocial3 = new ObraSocial(3,"Swiss Medical");
        obraSocialDao.registrar(obraSocial1);
        obraSocialDao.registrar(obraSocial2);
        obraSocialDao.registrar(obraSocial3);

        obraSocialDao.elimnar(2);

        assertNull(obraSocialDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        ObraSocial obraSocial2 = new ObraSocial(2,"SanCorSalud");
        ObraSocial obraSocial3 = new ObraSocial(3,"Swiss Medical");
        obraSocialDao.registrar(obraSocial1);
        obraSocialDao.registrar(obraSocial2);
        obraSocialDao.registrar(obraSocial3);

        ObraSocial obraSocialModificada = new ObraSocial(2,"SanCorSalud");
        obraSocialDao.modificar(obraSocialModificada);

        assertEquals(obraSocialModificada, obraSocialDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        ObraSocial obraSocialModificada = new ObraSocial(2,"SanCorSalud");
        try {
            obraSocialDao.modificar(obraSocialModificada);
        } catch (IllegalArgumentException e) {
            assertEquals("La obrasocial con id 2 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        ObraSocial obraSocial1 = new ObraSocial(1,"OSDE");
        ObraSocial obraSocial2 = new ObraSocial(2,"SanCorSalud");
        ObraSocial obraSocial3 = new ObraSocial(3,"Swiss Medical");
        obraSocialDao.registrar(obraSocial1);
        obraSocialDao.registrar(obraSocial2);
        obraSocialDao.registrar(obraSocial3);

        assertEquals(obraSocial1, obraSocialDao.buscarPorId(1));
        assertEquals(obraSocial2, obraSocialDao.buscarPorId(2));
        assertEquals(obraSocial3, obraSocialDao.buscarPorId(3));
        assertEquals(3, obraSocialDao.listarTodos().size());
    }
}