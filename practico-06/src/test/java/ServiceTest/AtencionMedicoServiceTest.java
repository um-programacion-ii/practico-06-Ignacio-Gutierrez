package ServiceTest;

import Dao.Interfaces.*;
import Entidades.*;
import Servicios.AtencionMedicoService;
import Servicios.GestionTurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AtencionMedicoServiceTest {
    private AtencionMedicoService atencionMedicoService;
    private ContenedorMemoria contenedorMemoria;

    @BeforeEach
    public void setUp() {
        contenedorMemoria = Mockito.mock(ContenedorMemoria.class);
        EspecialidadDAO especialidadDAO = Mockito.mock(EspecialidadDAO.class);
        MedicoDAO medicoDAO = Mockito.mock(MedicoDAO.class);
        TurnoDAO turnoDAO = Mockito.mock(TurnoDAO.class);
        MedicamentoDAO medicamentoDAO = Mockito.mock(MedicamentoDAO.class);
        RecetaDAO recetaDAO = Mockito.mock(RecetaDAO.class);
        Mockito.when(contenedorMemoria.getEspecialidadDao()).thenReturn(especialidadDAO);
        Mockito.when(contenedorMemoria.getTurnoDao()).thenReturn(turnoDAO);
        Mockito.when(contenedorMemoria.getMedicoDao()).thenReturn(medicoDAO);
        Mockito.when(contenedorMemoria.getMedicamentoDao()).thenReturn(medicamentoDAO);
        Mockito.when(contenedorMemoria.getRecetaDao()).thenReturn(recetaDAO);
        atencionMedicoService = AtencionMedicoService.getInstancia(contenedorMemoria);
    }

    @Test
    void iniciarConsultaTest() {
        ObraSocial osde = new ObraSocial(1,"OSDE");
        Paciente juliaLopezPaciente = new Paciente(1, "Julia", "Lopez", osde);

        Especialidad cardiología = new Especialidad(1, "Cardiología");
        List<ObraSocial> obrasSocialesAceptadasA = List.of(osde);

        Medico juanPerez = new Medico(1, "Juan","Perez", cardiología, obrasSocialesAceptadasA);

        Turno turno = new Turno(1, juliaLopezPaciente, juanPerez, false, "Pendiente");

        Turno turnoIniciado = new Turno(1, juliaLopezPaciente, juanPerez, false, "En curso");

        Turno turnoResultado = atencionMedicoService.iniciarConsulta(turno);

        assertEquals(turnoIniciado, turnoResultado);

    }

    @Test
    void generarNoRecetaTest() {
        Turno turno = new Turno(1, null, null, false, "En curso");

        List<Medicamento> medicamentos = Collections.emptyList();

        Receta recetaGenerada = atencionMedicoService.generarReceta(turno, medicamentos);

        assertEquals(recetaGenerada, null);
    }

    @Test
    void generarRecetaTest() {
        ObraSocial osde = new ObraSocial(1,"OSDE");
        Paciente juliaLopezPaciente = new Paciente(1, "Julia", "Lopez", osde);

        Especialidad cardiología = new Especialidad(1, "Cardiología");
        List<ObraSocial> obrasSocialesAceptadasA = List.of(osde);

        Medico juanPerez = new Medico(1, "Juan","Perez", cardiología, obrasSocialesAceptadasA);

        Turno turno = new Turno(1, juliaLopezPaciente, juanPerez, false, "En curso");

        Medicamento omeprazol = new Medicamento(3,"Omeprazol", 2);
        List<Medicamento> medicamentos = Collections.singletonList(omeprazol);

        Mockito.when(contenedorMemoria.getRecetaDao().listarTodos()).thenReturn(Collections.emptyList());

        Receta recetaGenerada = atencionMedicoService.generarReceta(turno, medicamentos);

        Mockito.when(contenedorMemoria.getRecetaDao().buscarPorId(1)).thenReturn(new Receta(1, medicamentos, juanPerez, juliaLopezPaciente));

        assertEquals(recetaGenerada, contenedorMemoria.getRecetaDao().buscarPorId(1));
    }

    @Test
    void finalizarConsultaTest() {
        ObraSocial osde = new ObraSocial(1,"OSDE");
        Paciente juliaLopezPaciente = new Paciente(1, "Julia", "Lopez", osde);

        Especialidad cardiología = new Especialidad(1, "Cardiología");
        List<ObraSocial> obrasSocialesAceptadasA = List.of(osde);

        Medico juanPerez = new Medico(1, "Juan","Perez", cardiología, obrasSocialesAceptadasA);

        Turno turno = new Turno(1, juliaLopezPaciente, juanPerez, false, "En curso");

        Turno turnoFinalizado = new Turno(1, juliaLopezPaciente, juanPerez, false, "Finalizada");

        Mockito.when(contenedorMemoria.getTurnoDao().buscarPorId(1)).thenReturn(turnoFinalizado);

        atencionMedicoService.finalizarConsulta(turno);

        assertEquals(turnoFinalizado, contenedorMemoria.getTurnoDao().buscarPorId(1));
    }

    @Test
    void getInstanciaTest() {
        AtencionMedicoService instancia1 = AtencionMedicoService.getInstancia(contenedorMemoria);
        AtencionMedicoService instancia2 = AtencionMedicoService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }

}
