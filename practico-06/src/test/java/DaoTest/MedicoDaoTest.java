package DaoTest;

import Dao.MedicoDAO;
import Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MedicoDaoTest {
    private MedicoDAO medicoDao;
    List<ObraSocial> obrasSocialesAceptadasList1 = new ArrayList<>();
    List<ObraSocial> obrasSocialesAceptadasList2 = new ArrayList<>();
    List<ObraSocial> obrasSocialesAceptadasList3 = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        medicoDao = new MedicoDaoImpl();

        obrasSocialesAceptadasList1.add(osde);
        obrasSocialesAceptadasList1.add(galeno);
        obrasSocialesAceptadasList2.add(osde);
        obrasSocialesAceptadasList2.add(sanCorSalud);
        obrasSocialesAceptadasList3.add(osde);
        obrasSocialesAceptadasList3.add(sanCorSalud);
        obrasSocialesAceptadasList3.add(swissMedical);
    }
    Especialidad dermatologia = new Especialidad(1,"Dermatologia");
    Especialidad pediatria = new Especialidad(2,"Pediatría");
    Especialidad oftalmologia = new Especialidad(3,"Oftalmología");

    ObraSocial osde = new ObraSocial(1,"OSDE");
    ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
    ObraSocial swissMedical = new ObraSocial(3,"Swiss Medical");
    ObraSocial galeno = new ObraSocial(4,"Galeno");

    @Test
    public void registrarYbucarTodosTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        Medico medico2 = new Medico(2,"Pedro","Gomez",pediatria,obrasSocialesAceptadasList2);
        Medico medico3 = new Medico(3,"Maria","Lopez",oftalmologia,obrasSocialesAceptadasList3);
        medicoDao.registrar(medico1);
        medicoDao.registrar(medico2);
        medicoDao.registrar(medico3);

        assertEquals(medico1, medicoDao.buscarPorId(1));
        assertEquals(medico2, medicoDao.buscarPorId(2));
        assertEquals(medico3, medicoDao.buscarPorId(3));
    }

    @Test
    public void agregarMedicamentoExistenteTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        medicoDao.registrar(medico1);
        try {
            medicoDao.registrar(medico1);
        } catch (IllegalArgumentException e) {
            assertEquals("El medico con id 1 ya existe.", e.getMessage());
        }
    }

    @Test
    public void buscarPorIdTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        medicoDao.registrar(medico1);
        assertEquals(medico1, medicoDao.buscarPorId(1));

    }

    @Test
    public void buscarPorNombreExisteTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        medicoDao.registrar(medico1);
        assertEquals(medico1, medicoDao.buscarPorNombre("Juan"));

    }

    @Test
    public void buscarPorNombreNoExisteTest() {
        try {
            Medico medico = medicoDao.buscarPorNombre("Juan");
        } catch (NoSuchElementException e) {
            assertEquals("No existe Juan.", e.getMessage());
        }
    }

    @Test
    public void buscarPorApellidoExisteTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        medicoDao.registrar(medico1);
        assertEquals(medico1, medicoDao.buscarPorApellido("Perez"));

    }

    @Test
    public void buscarPorApellidoNoExisteTest() {
        try {
            Medico medico = medicoDao.buscarPorApellido("Perez");
        } catch (NoSuchElementException e) {
            assertEquals("No existe Perez.", e.getMessage());
        }
    }

    @Test
    public void buscarPorEspecialidadExisteTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        medicoDao.registrar(medico1);
        assertEquals(medico1, medicoDao.buscarPorEspecialidad(dermatologia));

    }

    @Test
    public void buscarPorEspecialidadNoExisteTest() {
        try {
            Medico medico = medicoDao.buscarPorEspecialidad(dermatologia);
        } catch (NoSuchElementException e) {
            assertEquals("No existe Dermatologia.", e.getMessage());
        }
    }

    @Test
    public void buscarPorObraSocialExisteTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        medicoDao.registrar(medico1);
        assertEquals(medico1, medicoDao.buscarPorObraSocial(osde));

    }

    @Test
    public void buscarPorObraSocialNoExisteTest() {
        try {
            Medico medico = medicoDao.buscarPorObraSocial(osde);
        } catch (NoSuchElementException e) {
            assertEquals("No existe OSDE.", e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        Medico medico2 = new Medico(2,"Pedro","Gomez",pediatria,obrasSocialesAceptadasList2);
        Medico medico3 = new Medico(3,"Maria","Lopez",oftalmologia,obrasSocialesAceptadasList3);
        medicoDao.registrar(medico1);
        medicoDao.registrar(medico2);
        medicoDao.registrar(medico3);

        medicoDao.elimnar(2);

        assertNull(medicoDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        Medico medico2 = new Medico(2,"Pedro","Gomez",pediatria,obrasSocialesAceptadasList2);
        Medico medico3 = new Medico(3,"Maria","Lopez",oftalmologia,obrasSocialesAceptadasList3);
        medicoDao.registrar(medico1);
        medicoDao.registrar(medico2);
        medicoDao.registrar(medico3);

        Medico medicoModificado = new Medico(2,"Pedro","Gomez",pediatria,obrasSocialesAceptadasList3);

        medicoDao.modificar(medicoModificado);

        assertEquals(medicoModificado, medicoDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        Medico medicoModificado = new Medico(2,"Pedro","Gomez",pediatria,obrasSocialesAceptadasList2);
        try {
            medicoDao.modificar(medicoModificado);
        } catch (IllegalArgumentException e) {
            assertEquals("El medico con id 2 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
        Medico medico2 = new Medico(2,"Pedro","Gomez",pediatria,obrasSocialesAceptadasList2);
        Medico medico3 = new Medico(3,"Maria","Lopez",oftalmologia,obrasSocialesAceptadasList3);
        medicoDao.registrar(medico1);
        medicoDao.registrar(medico2);
        medicoDao.registrar(medico3);

        assertEquals(medico1, medicoDao.buscarPorId(1));
        assertEquals(medico2, medicoDao.buscarPorId(2));
        assertEquals(medico3, medicoDao.buscarPorId(3));
        assertEquals(3, medicoDao.listarTodos().size());
    }

}
