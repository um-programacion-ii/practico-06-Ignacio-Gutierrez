package DaoTest;

import Dao.TurnoDAO;
import Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TurnoDaoTest {
    private TurnoDAO turnoDao;

    @BeforeEach
    public void setUp() {
        turnoDao = new TurnoDaoImpl();
    }

    Especialidad dermatologia = new Especialidad(1,"Dermatologia");
    Especialidad pediatria = new Especialidad(2,"Pediatría");
    Especialidad oftalmologia = new Especialidad(3,"Oftalmología");

    ObraSocial osde = new ObraSocial(1,"OSDE");
    ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
    ObraSocial swissMedical = new ObraSocial(3,"Swiss Medical");

    Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
    Paciente paciente2 = new Paciente(2,"Pedro","Gomez",sanCorSalud);
    Paciente paciente3 = new Paciente(3,"Maria","Lopez",swissMedical);

    Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,osde);
    Medico medico2 = new Medico(2,"Pedro","Gomez",pediatria,sanCorSalud);
    Medico medico3 = new Medico(3,"Maria","Lopez",oftalmologia,swissMedical);

    @Test
    public void registrarYbucarTodosTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,osde);
        Turno turno2 = new Turno(2, paciente2, medico2,sanCorSalud);
        Turno turno3 = new Turno(3, paciente3, medico3,swissMedical);

        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        assertEquals(turno1, turnoDao.buscarPorId(1));
        assertEquals(turno2, turnoDao.buscarPorId(2));
        assertEquals(turno3, turnoDao.buscarPorId(3));
    }

    public void buscarPorIdTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,osde);
        turnoDao.registrar(turno1);
        assertEquals(turno1, turnoDao.buscarPorId(1));
    }


    @Test
    public void buscarPorPacienteExisteTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,osde);
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
        Turno turno1 = new Turno(1, paciente1, medico1,osde);
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
    public void eliminarTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,osde);
        Turno turno2 = new Turno(2, paciente2, medico2,sanCorSalud);
        Turno turno3 = new Turno(3, paciente3, medico3,swissMedical);
        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        turnoDao.elimnar(2);

        assertNull(turnoDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,osde);
        Turno turno2 = new Turno(2, paciente2, medico2,sanCorSalud);
        Turno turno3 = new Turno(3, paciente3, medico3,swissMedical);
        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        Turno turnoModificado = new Turno(2, paciente2, medico2,sanCorSalud);

        turnoDao.modificar(turnoModificado);

        assertEquals(turnoModificado, turnoDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        Turno turnoModificado = new Turno(2, paciente2, medico2,sanCorSalud);
        try {
            turnoDao.modificar(turnoModificado);
        } catch (NoSuchElementException e) {
            assertEquals("El turno con id 2 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        Turno turno1 = new Turno(1, paciente1, medico1,osde);
        Turno turno2 = new Turno(2, paciente2, medico2,sanCorSalud);
        Turno turno3 = new Turno(3, paciente3, medico3,swissMedical);
        turnoDao.registrar(turno1);
        turnoDao.registrar(turno2);
        turnoDao.registrar(turno3);

        assertEquals(turno1, turnoDao.buscarPorId(1));
        assertEquals(turno2, turnoDao.buscarPorId(2));
        assertEquals(turno3, turnoDao.buscarPorId(3));
        assertEquals(3, turnoDao.listarTodos().size());
    }

}
