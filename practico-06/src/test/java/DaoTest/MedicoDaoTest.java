package DaoTest;

import Dao.MedicoDAO;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.MedicoDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicoDaoTest {
    private MedicoDAO medicoDao;

    @BeforeEach
    public void setUp() {
        medicoDao = new MedicoDaoImpl();
    }

    @Test
    public void registrarYbucarTodosTest() {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,null);
        Medico medico2 = new Medico(2,"Pedro","Gomez",null,null);
        Medico medico3 = new Medico(3,"Maria","Lopez",null,null);
        medicoDao.registrar(medico1);
        medicoDao.registrar(medico2);
        medicoDao.registrar(medico3);

        assertEquals(medico1, medicoDao.buscarPorId(1));
        assertEquals(medico2, medicoDao.buscarPorId(2));
        assertEquals(medico3, medicoDao.buscarPorId(3));

    }
}
