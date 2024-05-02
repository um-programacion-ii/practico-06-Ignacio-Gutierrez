package DaoTest;

import Dao.RecetaDAO;
import Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RecetaDaoTest {
    private RecetaDAO recetaDao;
    List<Medicamento> medicamentosList1 = new ArrayList<>();
    List<Medicamento> medicamentosList2 = new ArrayList<>();
    List<Medicamento> medicamentosList3 = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        recetaDao = new RecetaDaoImpl();

        medicamentosList1.add(medicamento1);
        medicamentosList2.add(medicamento1);
        medicamentosList2.add(medicamento2);
        medicamentosList3.add(medicamento2);
        medicamentosList3.add(medicamento3);
    }
    Especialidad dermatologia = new Especialidad(1,"Dermatologia");
    Especialidad pediatria = new Especialidad(2,"Pediatría");
    Especialidad oftalmologia = new Especialidad(3,"Oftalmología");

    ObraSocial osde = new ObraSocial(1,"OSDE");
    ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
    ObraSocial swissMedical = new ObraSocial(3,"Swiss Medical");

    Medicamento medicamento1 = new Medicamento(1,"Paracetamol", 10);
    Medicamento medicamento2 = new Medicamento(2,"Ibuprofeno", 20);
    Medicamento medicamento3 = new Medicamento(3,"Omeprazol", 30);

    Paciente paciente1 = new Paciente(1,"Juan","Perez",osde);
    Paciente paciente2 = new Paciente(2,"Pedro","Gomez",sanCorSalud);
    Paciente paciente3 = new Paciente(3,"Maria","Lopez",swissMedical);

    Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,osde);
    Medico medico2 = new Medico(2,"Pedro","Gomez",pediatria,sanCorSalud);
    Medico medico3 = new Medico(3,"Maria","Lopez",oftalmologia,swissMedical);

    @Test
    public void registrarYbucarTodosTest() {
        Receta receta1 = new Receta(1,medicamentosList1,medico1,paciente1);
        Receta receta2 = new Receta(2,medicamentosList2,medico2,paciente2);
        Receta receta3 = new Receta(3,medicamentosList3,medico3,paciente3);

        recetaDao.registrar(receta1);
        recetaDao.registrar(receta2);
        recetaDao.registrar(receta3);

        assertEquals(receta1, recetaDao.buscarPorId(1));
        assertEquals(receta2, recetaDao.buscarPorId(2));
        assertEquals(receta3, recetaDao.buscarPorId(3));
    }

    @Test
    public void buscarPorIdTest() {
        Receta receta1 = new Receta(1,medicamentosList1,medico1,paciente1);
        recetaDao.registrar(receta1);
        assertEquals(receta1, recetaDao.buscarPorId(1));

    }

    @Test
    public void buscarPorPacienteExisteTest() {
        Receta receta1 = new Receta(1,medicamentosList1,medico1,paciente1);
        recetaDao.registrar(receta1);
        assertEquals(receta1, recetaDao.buscarPorPaciente(paciente1));

    }

    @Test
    public void buscarPorPacienteNoExisteTest() {
        try {
            recetaDao.buscarPorPaciente(paciente1);
        } catch (NoSuchElementException e) {
            assertEquals("No existe Juan.", e.getMessage());
        }
    }

    @Test
    public void buscarPorMedicoExisteTest() {
        Receta receta1 = new Receta(1,medicamentosList1,medico1,paciente1);
        recetaDao.registrar(receta1);
        assertEquals(receta1, recetaDao.buscarPorMedico(medico1));

    }

    @Test
    public void buscarPorMedicoNoExisteTest() {
        try {
            recetaDao.buscarPorMedico(medico1);
        } catch (NoSuchElementException e) {
            assertEquals("No existe Juan.", e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        Receta receta1 = new Receta(1,medicamentosList1,medico1,paciente1);
        Receta receta2 = new Receta(2,medicamentosList2,medico2,paciente2);
        Receta receta3 = new Receta(3,medicamentosList3,medico3,paciente3);
        recetaDao.registrar(receta1);
        recetaDao.registrar(receta2);
        recetaDao.registrar(receta3);

        recetaDao.elimnar(2);

        assertNull(recetaDao.buscarPorId(2));
    }

    @Test
    public void modificarExisteTest() {
        Receta receta1 = new Receta(1,medicamentosList1,medico1,paciente1);
        Receta receta2 = new Receta(2,medicamentosList2,medico2,paciente2);
        Receta receta3 = new Receta(3,medicamentosList3,medico3,paciente3);
        recetaDao.registrar(receta1);
        recetaDao.registrar(receta2);
        recetaDao.registrar(receta3);

        Receta recetaModificada = new Receta(2,medicamentosList1,medico2,paciente1);

        recetaDao.modificar(recetaModificada);

        assertEquals(recetaModificada, recetaDao.buscarPorId(2));
    }

    @Test
    public void modificarNoExisteTest() {
        Receta recetaModificada = new Receta(2,medicamentosList1,medico2,paciente1);
        try {
            recetaDao.modificar(recetaModificada);
        } catch (NoSuchElementException e) {
            assertEquals("La receta con id 2 no existe.", e.getMessage());
        }
    }


    @Test
    public void listarTodosTest() {
        Receta receta1 = new Receta(1,medicamentosList1,medico1,paciente1);
        Receta receta2 = new Receta(2,medicamentosList2,medico2,paciente2);
        Receta receta3 = new Receta(3,medicamentosList3,medico3,paciente3);
        recetaDao.registrar(receta1);
        recetaDao.registrar(receta2);
        recetaDao.registrar(receta3);

        assertEquals(receta1, recetaDao.buscarPorId(1));
        assertEquals(receta2, recetaDao.buscarPorId(2));
        assertEquals(receta3, recetaDao.buscarPorId(3));
        assertEquals(3, recetaDao.listarTodos().size());
    }
}
