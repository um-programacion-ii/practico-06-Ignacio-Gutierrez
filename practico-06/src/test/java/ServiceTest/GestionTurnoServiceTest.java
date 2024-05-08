package ServiceTest;

import Dao.Interfaces.EspecialidadDAO;
import Dao.Interfaces.MedicoDAO;
import Dao.Interfaces.TurnoDAO;
import Entidades.*;
import Servicios.GestionTurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class GestionTurnoServiceTest {
    private GestionTurnoService gestionTurnoService;
    private ContenedorMemoria contenedorMemoria;

    @BeforeEach
    public void setUp() {
        contenedorMemoria = Mockito.mock(ContenedorMemoria.class);
        EspecialidadDAO especialidadDAO = Mockito.mock(EspecialidadDAO.class);
        MedicoDAO medicoDAO = Mockito.mock(MedicoDAO.class);
        TurnoDAO turnoDAO = Mockito.mock(TurnoDAO.class);
        Mockito.when(contenedorMemoria.getEspecialidadDao()).thenReturn(especialidadDAO);
        Mockito.when(contenedorMemoria.getTurnoDao()).thenReturn(turnoDAO);
        Mockito.when(contenedorMemoria.getMedicoDao()).thenReturn(medicoDAO);
        gestionTurnoService = GestionTurnoService.getInstancia(contenedorMemoria);
    }

    @Test
    void listarEspecialidadesTest() {
        //Solo si se testea de forma individual verifica
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");
        List<Especialidad> especialidades = Arrays.asList(especialidad1, especialidad2);

        Mockito.when(contenedorMemoria.getEspecialidadDao().listarTodos()).thenReturn(especialidades);

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        Especialidad especialidadSeleccionada = gestionTurnoService.listarEspecialidades();

        assertEquals(especialidad1, especialidadSeleccionada);
    }

    @Test
    void listarEspecialidadesFueraDeRangoTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");
        List<Especialidad> especialidades = Arrays.asList(especialidad1, especialidad2);

        Mockito.when(contenedorMemoria.getEspecialidadDao().listarTodos()).thenReturn(especialidades);

        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        try {
            gestionTurnoService.listarEspecialidades();
        } catch (IllegalArgumentException e) {
            assertEquals("Número de especialidad inválido", e.getMessage());
        }
    }


    @Test
    void listarMedicosPorEspecialidadNoExisteTest() {
        //Solo si se testea de forma individual verifica
        Especialidad especialidad = new Especialidad(1,"Dermatologia");
        ObraSocial osde = new ObraSocial(1,"OSDE");
        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Boolean particular = true;
        List<Medico> listaMedVacia = new ArrayList<>();

        Mockito.when(contenedorMemoria.getMedicoDao().buscarPorEspecialidad(especialidad)).thenReturn(listaMedVacia);

        try {
            gestionTurnoService.listarMedicosPorEspecialidad(especialidad, paciente, particular);
        } catch (RuntimeException e) {
            assertEquals("No hay médicos disponibles para la especialidad seleccionada", e.getMessage());
        }
    }

    @Test
    void listarMedicosPorEspecialidadTest() {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, sanCorSalud);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);
        Medico medico2 = new Medico(2,"Pedro","Gomez",dermatologia,obrasSocialesAceptadasList);
        List<Medico> medicos = Arrays.asList(medico1, medico2);

        Mockito.when(contenedorMemoria.getMedicoDao().buscarPorEspecialidad(dermatologia)).thenReturn(medicos);

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        Medico medicoSeleccionado = gestionTurnoService.listarMedicosPorEspecialidad(dermatologia, paciente, false);

        assertEquals(medico1, medicoSeleccionado);
    }

    @Test
    void darTurnoAPacienteTest() {
        //Solo si se testea de forma individual verifica
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, sanCorSalud);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);

        List<Turno> turnos = Collections.nCopies(0, null);
        Mockito.when(contenedorMemoria.getTurnoDao().listarTodos()).thenReturn(turnos);

        gestionTurnoService.darTurnoAPaciente(paciente, medico, true);

        ArgumentCaptor<Turno> argumentCaptor = ArgumentCaptor.forClass(Turno.class);
        Mockito.verify(contenedorMemoria.getTurnoDao()).registrar(argumentCaptor.capture());
        Turno turnoRegistrado = argumentCaptor.getValue();

        assertEquals(0, turnoRegistrado.getId());
        assertEquals(paciente, turnoRegistrado.getPaciente());
        assertEquals(medico, turnoRegistrado.getMedico());
        assertEquals(true, turnoRegistrado.getParticular());
        assertEquals("Pendiente", turnoRegistrado.getEstadoTurno());

    }

    @Test
    void getInstanciaTest() {
        GestionTurnoService instancia1 = GestionTurnoService.getInstancia(contenedorMemoria);
        GestionTurnoService instancia2 = GestionTurnoService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }

}
