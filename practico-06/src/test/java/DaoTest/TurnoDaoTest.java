package DaoTest;

import Dao.TurnoDAO;
import Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TurnoDaoTest {
    private TurnoDAO turnoDao;
    List<ObraSocial> obrasSocialesAceptadasList1 = new ArrayList<>();
    List<ObraSocial> obrasSocialesAceptadasList2 = new ArrayList<>();
    List<ObraSocial> obrasSocialesAceptadasList3 = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        turnoDao = new TurnoDaoImpl();

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

    Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
    Paciente paciente2 = new Paciente(2,"Pedro","Gomez",sanCorSalud);
    Paciente paciente3 = new Paciente(3,"Maria","Lopez",swissMedical);

    Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList1);
    Medico medico2 = new Medico(2,"Pedro","Gomez",pediatria,obrasSocialesAceptadasList2);
    Medico medico3 = new Medico(3,"Maria","Lopez",oftalmologia,obrasSocialesAceptadasList3);

    @Test
    public void registrarYbucarTodosTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        Turno turno2 = new Turno(2, paciente2, medico2,true,"Completado");
        Turno turno3 = new Turno(3, paciente3, medico3,false,"Pendiente");

        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        assertEquals(turno1, turnoDao.buscarPorId(1));
        assertEquals(turno2, turnoDao.buscarPorId(2));
        assertEquals(turno3, turnoDao.buscarPorId(3));
    }

    public void buscarPorIdTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        turnoDao.registrar(turno1);
        assertEquals(turno1, turnoDao.buscarPorId(1));
    }


    @Test
    public void buscarPorPacienteExisteTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        turnoDao.registrar(turno1);
        assertEquals(turno1, turnoDao.buscarPorPaciente(paciente1));
    }

    @Test
    public void buscarPorPacienteNoExisteTest() {
        try {
            turnoDao.buscarPorPaciente(paciente1);
        } catch (NoSuchElementException e) {
            assertEquals("No existe Juan.", e.getMessage());
        }
    }

    @Test
    public void buscarPorMedicoExisteTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        turnoDao.registrar(turno1);
        assertEquals(turno1, turnoDao.buscarPorMedico(medico1));

    }

    @Test
    public void buscarPorMedicoNoExisteTest() {
        try {
            turnoDao.buscarPorMedico(medico1);
        } catch (NoSuchElementException e) {
            assertEquals("No existe Juan.", e.getMessage());
        }
    }

    @Test
    public void buscarPorEstadoExisteTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        turnoDao.registrar(turno1);
        assertEquals(turno1, turnoDao.buscarPorEstado("Pendiente"));

    }

    @Test
    public void buscarPorEstadoNoExisteTest() {
        try {
            turnoDao.buscarPorEstado("Pendiente");
        } catch (NoSuchElementException e) {
            assertEquals("No hay turnos Pendiente.", e.getMessage());
        }
    }

    @Test
    public void buscarPorParticularExisteTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        turnoDao.registrar(turno1);
        assertEquals(turno1, turnoDao.buscarPorParticular(true));

    }

    @Test
    public void buscarPorParticularNoExisteTest() {
        try {
            turnoDao.buscarPorParticular(true);
        } catch (NoSuchElementException e) {
            assertEquals("No hay turnos true.", e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        Turno turno2 = new Turno(2, paciente2, medico2,true,"Completado");
        Turno turno3 = new Turno(3, paciente3, medico3,false,"Pendiente");
        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        turnoDao.elimnar(2);

        assertNull(turnoDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        Turno turno2 = new Turno(2, paciente2, medico2,true,"Completado");
        Turno turno3 = new Turno(3, paciente3, medico3,false,"Pendiente");
        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        Turno turnoModificado = new Turno(2, paciente2, medico2,true,"Completado");

        turnoDao.modificar(turnoModificado);

        assertEquals(turnoModificado, turnoDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        Turno turnoModificado = new Turno(2, paciente2, medico2,true,"Completado");
        try {
            turnoDao.modificar(turnoModificado);
        } catch (NoSuchElementException e) {
            assertEquals("El turno con id 2 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,true,"Pendiente");
        Turno turno2 = new Turno(2, paciente2, medico2,true,"Completado");
        Turno turno3 = new Turno(3, paciente3, medico3,false,"Pendiente");
        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        assertEquals(turno1, turnoDao.buscarPorId(1));
        assertEquals(turno2, turnoDao.buscarPorId(2));
        assertEquals(turno3, turnoDao.buscarPorId(3));
        assertEquals(3, turnoDao.listarTodos().size());
    }

}
