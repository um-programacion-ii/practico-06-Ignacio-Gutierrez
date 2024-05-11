package ServiceTest;

import Dao.Interfaces.*;
import Entidades.*;
import Servicios.GestionDrogueriaService;
import Servicios.GestionFarmaciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class GestionFarmaciaSericeTest {
    private GestionFarmaciaService gestionFarmaciaService;
    private GestionDrogueriaService gestionDrogueriaService;
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
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);

        Turno turno1 = new Turno(1, paciente, medico1, false, "Pendiente");

        Receta receta = new Receta(1, medicamentos, medico1, paciente);

        try {
            gestionFarmaciaService.compraDeMedicamentos(receta, turno1);
        } catch (Exception e) {
            assertEquals("El turno no está finalizado", e.getMessage());
        }
    }

    @Test
    public void compraMedicamentosConTurnoFinalizadoStockSuficienteTest() throws Exception {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial swissMedical = new ObraSocial(2,"Swiss Medical");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, swissMedical);

        Medicamento medicamento1 = new Medicamento(1,"Ibuprofeno", 10);

        List<Medicamento> medicamentos = Arrays.asList(medicamento1);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);

        Turno turno = new Turno(1, paciente, medico1, false, "Finalizada");

        Receta receta = new Receta(1, medicamentos, medico1, paciente);

        gestionFarmaciaService.compraDeMedicamentos(receta, turno);

        Mockito.verify(contenedorMemoria.getMedicamentoDao()).retirarCantidadPorId(1, 10);
    }

    @Test
    void getInstanciaTest() {
        GestionFarmaciaService instancia1 = GestionFarmaciaService.getInstancia(contenedorMemoria);
        GestionFarmaciaService instancia2 = GestionFarmaciaService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }
}
