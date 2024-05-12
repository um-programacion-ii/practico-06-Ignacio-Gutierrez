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
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GestionTurnoServiceCasoBTest {
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

    /*
    Estos Test evaluan el método listarEspecialidades de la clase GestionTurnoService
    cuando no hay especialidades en el sistema
    */

    @Test
    void listarEspecialidadesVaciaTest() {
        Mockito.when(contenedorMemoria.getEspecialidadDao().listarTodos())
                .thenReturn(Collections.emptyList());

        try {
            gestionTurnoService.listarEspecialidades();
        } catch (RuntimeException e) {
            assertEquals("No hay especialidades disponibles", e.getMessage());
        }
    }

}
