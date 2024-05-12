package ServiceTest;

import Dao.Interfaces.*;
import Entidades.*;
import Servicios.GestionFarmaciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class GestionFarmaciaSericeTest {
    private GestionFarmaciaService gestionFarmaciaService;
    private ContenedorMemoria contenedorMemoria;

    @BeforeEach
    public void setUp() {
        contenedorMemoria = Mockito.mock(ContenedorMemoria.class);
        EspecialidadDAO especialidadDAO = Mockito.mock(EspecialidadDAO.class);
        TurnoDAO turnoDAO = Mockito.mock(TurnoDAO.class);
        MedicamentoDAO medicamentoDAO = Mockito.mock(MedicamentoDAO.class);
        RecetaDAO recetaDAO = Mockito.mock(RecetaDAO.class);
        Mockito.when(contenedorMemoria.getEspecialidadDao()).thenReturn(especialidadDAO);
        Mockito.when(contenedorMemoria.getTurnoDao()).thenReturn(turnoDAO);
        Mockito.when(contenedorMemoria.getMedicamentoDao()).thenReturn(medicamentoDAO);
        Mockito.when(contenedorMemoria.getRecetaDao()).thenReturn(recetaDAO);

        try {
            Field instanciaField = GestionFarmaciaService.class.getDeclaredField("instancia");
            instanciaField.setAccessible(true);
            instanciaField.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        gestionFarmaciaService = GestionFarmaciaService.getInstancia(contenedorMemoria);
    }

    @Test
    public void compraMedicamentosSinIrAlTurnoTest() {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial swissMedical = new ObraSocial(2,"Swiss Medical");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, swissMedical);

        Medicamento medicamento1 = new Medicamento(1,"Ibuprofeno", 10);
        Medicamento medicamento2 = new Medicamento(2,"Paracetamol", 5);
        List<Medicamento> medicamentos = Arrays.asList(medicamento1, medicamento2);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);

        Turno turno = new Turno(1, paciente, medico, false, "Pendiente");

        Receta receta = new Receta(1, medicamentos, medico, paciente);

        try {
            gestionFarmaciaService.compraDeMedicamentos(receta, turno);
        } catch (Exception e) {
            assertEquals("El turno no está finalizado", e.getMessage());
        }
    }

    @Test
    public void compraMedicamentosConTurnoFinalizadoStockSuficienteTest() {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial swissMedical = new ObraSocial(2,"Swiss Medical");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, swissMedical);

        Medicamento medicamento1 = new Medicamento(1,"Ibuprofeno", 10);
        Medicamento medicamento2 = new Medicamento(2,"Paracetamol", 5);
        List<Medicamento> medicamentos = Arrays.asList(medicamento1, medicamento2);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);

        Turno turno = new Turno(1, paciente, medico, false, "Finalizada");

        Receta receta = new Receta(1, medicamentos, medico, paciente);

        gestionFarmaciaService.compraDeMedicamentos(receta, turno);

        Mockito.verify(contenedorMemoria.getMedicamentoDao()).retirarCantidadPorId(1, 10);
    }

    @Test
    void compraMedicamentosConTurnoFinalizadoStockInsuficienteTest() {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial swissMedical = new ObraSocial(2,"Swiss Medical");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, swissMedical);

        Medicamento medicamento1 = new Medicamento(1,"Ibuprofeno", 10);
        Medicamento medicamento2 = new Medicamento(2,"Paracetamol", 5);
        List<Medicamento> medicamentos = Arrays.asList(medicamento1, medicamento2);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);

        Turno turno = new Turno(1, paciente, medico, false, "Finalizada");

        Receta receta = new Receta(1, medicamentos, medico, paciente);

        MedicamentoDAO medicamentoDAO = contenedorMemoria.getMedicamentoDao();

        Mockito.doThrow(new RuntimeException("La cantidad a retirar es mayor que la cantidad disponible."))
                .when(medicamentoDAO)
                .retirarCantidadPorId(1, 10);

        try {
            gestionFarmaciaService.compraDeMedicamentos(receta, turno);
        } catch (Exception e) {
            assertEquals("La cantidad a retirar es mayor que la cantidad disponible.", e.getMessage());
        }

        Mockito.verify(contenedorMemoria.getMedicamentoDao(),
                Mockito.times(2)).retirarCantidadPorId(1, 10);

    }


    @Test
    void getInstanciaTest() {
        GestionFarmaciaService instancia1 = GestionFarmaciaService.getInstancia(contenedorMemoria);
        GestionFarmaciaService instancia2 = GestionFarmaciaService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }
}
