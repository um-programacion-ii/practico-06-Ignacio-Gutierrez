package ServiceTest;

import Dao.Interfaces.EspecialidadDAO;
import Dao.Interfaces.MedicoDAO;
import Entidades.*;
import Servicios.GestionTurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
        Mockito.when(contenedorMemoria.getEspecialidadDao()).thenReturn(especialidadDAO);
        Mockito.when(contenedorMemoria.getMedicoDao()).thenReturn(medicoDAO);
        gestionTurnoService = GestionTurnoService.getInstancia(contenedorMemoria);
    }

    @Test
    void listarEspecialidadesTest() {
        Especialidad especialidad1 = new Especialidad(1,"Dermatologia");
        Especialidad especialidad2 = new Especialidad(2,"Pediatría");
        List<Especialidad> especialidades = Arrays.asList(especialidad1, especialidad2);

        Mockito.when(contenedorMemoria.getEspecialidadDao().listarTodos()).thenReturn(especialidades);

        System.setIn(new ByteArrayInputStream("1".getBytes()));

        Especialidad especialidadSeleccionada = gestionTurnoService.listarEspecialidades();

        assertEquals(especialidad1, especialidadSeleccionada);
    }

    @Test
    void listarMedicosPorEspecialidadNoExisteTest() {
        Especialidad especialidad = new Especialidad(1,"Dermatologia");
        ObraSocial osde = new ObraSocial(1,"OSDE");
        Paciente paciente = new Paciente(1,"Juan","Perez",osde);
        Boolean particular = false;

        Mockito.when(contenedorMemoria.getMedicoDao().buscarPorEspecialidad(especialidad)).thenReturn(new ArrayList<>());

        try {
            gestionTurnoService.listarMedicosPorEspecialidad(especialidad, paciente, particular);
        } catch (RuntimeException e) {
            assertEquals("No hay médicos disponibles para la especialidad seleccionada", e.getMessage());
        }
    }

    @Test
    void listarMedicosPorEspecialidadTest() {
    }

    @Test
    void getInstanciaTest() {
        GestionTurnoService instancia1 = GestionTurnoService.getInstancia(contenedorMemoria);
        GestionTurnoService instancia2 = GestionTurnoService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }

}
