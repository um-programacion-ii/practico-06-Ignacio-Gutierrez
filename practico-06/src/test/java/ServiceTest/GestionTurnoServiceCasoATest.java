package ServiceTest;

import Dao.Interfaces.EspecialidadDAO;
import Dao.Interfaces.MedicoDAO;
import Dao.Interfaces.TurnoDAO;
import Entidades.*;
import Servicios.GestionTurnoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionTurnoServiceCasoATest {
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

        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");

        Mockito.when(contenedorMemoria.getEspecialidadDao().listarTodos())
                .thenReturn(Arrays.asList(especialidad1, especialidad2));

    }


    @Test
    void listarEspecialidadesTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");

        Mockito.when(contenedorMemoria.getEspecialidadDao().listarTodos())
                .thenReturn(Arrays.asList(especialidad1, especialidad2));

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        Especialidad especialidadSeleccionada = gestionTurnoService.listarEspecialidades();

        assertEquals(especialidad1, especialidadSeleccionada);
    }



    @Test
    void listarEspecialidadesFueraDeRangoTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");

        Mockito.when(contenedorMemoria.getEspecialidadDao().listarTodos())
                .thenReturn(Arrays.asList(especialidad1, especialidad2));

        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        try {
            gestionTurnoService.listarEspecialidades();
        } catch (RuntimeException e) {
            assertEquals("Número de especialidad inválido", e.getMessage());
        }
    }


    @Test
    void seleccionarTipoTurnoParticularTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        Boolean resultado = gestionTurnoService.seleccionarTipoTurno();

        assertTrue(resultado);
    }

    @Test
    void seleccionarTipoTurnoObraSocialTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(in);

        Boolean resultado = gestionTurnoService.seleccionarTipoTurno();

        assertFalse(resultado);
    }

    @Test
    void seleccionarTipoTurnoInvalidoTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\n".getBytes());
        System.setIn(in);

        assertThrows(IllegalArgumentException.class, () -> gestionTurnoService.seleccionarTipoTurno());
    }

    @Test
    void listarMedicosPorEspecialidadNoExisteTest() {

        Especialidad dermatologia = new Especialidad(1,"Dermatologia");
        ObraSocial osde = new ObraSocial(1,"OSDE");
        Paciente paciente = new Paciente(1,"Juan","Perez",osde);

        Mockito.when(contenedorMemoria.getMedicoDao().buscarPorEspecialidad(Mockito.any(Especialidad.class))).thenReturn(new ArrayList<>());

        try {
            gestionTurnoService.listarMedicosPorEspecialidad(dermatologia, paciente, true);
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
    void listarMedicosPorEspecialidadNumeroInvalidoTest() {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, sanCorSalud);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);
        Medico medico2 = new Medico(2,"Pedro","Gomez",dermatologia,obrasSocialesAceptadasList);
        List<Medico> medicos = Arrays.asList(medico1, medico2);

        Mockito.when(contenedorMemoria.getMedicoDao().buscarPorEspecialidad(dermatologia)).thenReturn(medicos);

        ByteArrayInputStream in = new ByteArrayInputStream("40".getBytes());
        System.setIn(in);

        try {
            gestionTurnoService.listarMedicosPorEspecialidad(dermatologia, paciente, false);
        } catch (RuntimeException e) {
            assertEquals("Número de especialidad inválido", e.getMessage());
        }
    }

    @Test
    void crearUnTurnoTest() {
        Especialidad dermatologia = new Especialidad(1,"Dermatologia");

        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial sanCorSalud = new ObraSocial(2,"SanCorSalud");
        List<ObraSocial> obrasSocialesAceptadasList = Arrays.asList(osde, sanCorSalud);

        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Medico medico1 = new Medico(1,"Juan","Perez",dermatologia,obrasSocialesAceptadasList);

        Mockito.when(contenedorMemoria.getTurnoDao().listarTodos()).thenReturn(Collections.emptyList());

        Turno turno1 = new Turno(1, paciente, medico1, false, "Pendiente");

        Mockito.when(contenedorMemoria.getTurnoDao().buscarPorId(1)).thenReturn(turno1);

        gestionTurnoService.darTurnoAPaciente(paciente, medico1, false);

        assertEquals(turno1, contenedorMemoria.getTurnoDao().buscarPorId(1));
    }

    @Test
    void getInstanciaTest() {
        GestionTurnoService instancia1 = GestionTurnoService.getInstancia(contenedorMemoria);
        GestionTurnoService instancia2 = GestionTurnoService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }

}
