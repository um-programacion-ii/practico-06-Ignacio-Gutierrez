package ServiceTest;

import Dao.Interfaces.EspecialidadDAO;
import Dao.Interfaces.ObraSocialDAO;
import Dao.Interfaces.PacienteDAO;
import Entidades.ContenedorMemoria;
import Entidades.ObraSocial;
import Entidades.Paciente;
import Servicios.RegistroDePacientesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class RegistroDePacienteTest {
    private RegistroDePacientesService registroDePacientesService;
    private ContenedorMemoria contenedorMemoria;

    @BeforeEach
    public void setUp() {
        contenedorMemoria = Mockito.mock(ContenedorMemoria.class);
        EspecialidadDAO especialidadDAO = Mockito.mock(EspecialidadDAO.class);
        PacienteDAO pacienteDAO = Mockito.mock(PacienteDAO.class);
        ObraSocialDAO obraSocialDAO = Mockito.mock(ObraSocialDAO.class);
        Mockito.when(contenedorMemoria.getEspecialidadDao()).thenReturn(especialidadDAO);
        Mockito.when(contenedorMemoria.getPacienteDao()).thenReturn(pacienteDAO);
        Mockito.when(contenedorMemoria.getObraSocialDao()).thenReturn(obraSocialDAO);

        try {
            Field instanciaField = RegistroDePacientesService.class.getDeclaredField("instancia");
            instanciaField.setAccessible(true);
            instanciaField.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        registroDePacientesService = RegistroDePacientesService.getInstancia(contenedorMemoria);
    }

    @Test
    public void buscarPacienteNoExisteTest() {
        Paciente paciente1 = new Paciente(1, "Juan", "Gomez", null);
        Paciente paciente2 = new Paciente(2, "Nicolas", "Perez", null);
        //Paciente paciente3 = new Paciente(1, "Juan", "Perez", null);

        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorNombre("Juan")).thenReturn(Arrays.asList(paciente1));
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorApellido("Perez")).thenReturn(Arrays.asList(paciente2));

        try {
            registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");
        } catch (NoSuchElementException e) {
            assertEquals("Perez, Juan no se encuentra registrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void buscarPacienteListaVaciaTest() {
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorNombre("Juan")).thenReturn(Arrays.asList());
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorApellido("Perez")).thenReturn(Arrays.asList());

        try {
            registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");
        } catch (NoSuchElementException e) {
            assertEquals("Perez, Juan no se encuentra registrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void buscarPacienteSiExisteTest() {
        Paciente paciente1 = new Paciente(1, "Juan", "Gomez", null);
        Paciente paciente2 = new Paciente(2, "Nicolas", "Perez", null);
        Paciente paciente3 = new Paciente(3, "Juan", "Perez", null);

        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorNombre("Juan"))
                .thenReturn(Arrays.asList(paciente1, paciente3));
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorApellido("Perez"))
                .thenReturn(Arrays.asList(paciente2, paciente3));

        Paciente resultado = registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");

        assertEquals(paciente3, resultado);
    }

    @Test
    public void buscarPacienteNoExisteNadieTest() {
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorNombre("Juan"))
                .thenThrow(new NoSuchElementException("No existe Juan."));
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorApellido("Perez"))
                .thenThrow(new NoSuchElementException("No existe Perez."));

        try {
            registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");
        } catch (NoSuchElementException e) {
            assertEquals("Perez, Juan no se encuentra registrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void buscarPacienteNoExisteElBuscadoTest() {
        Paciente paciente1 = new Paciente(1, "Juan", "Gomez", null);
        Paciente paciente2 = new Paciente(2, "Nicolas", "Perez", null);
        Paciente paciente3 = new Paciente(3, "Juan", "Perez", null);

        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorNombre("Juan"))
                .thenReturn(Arrays.asList(paciente1));
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorApellido("Perez"))
                .thenReturn(Arrays.asList(paciente2));

        try {
            registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");
        } catch (NoSuchElementException e) {
            assertEquals("Perez, Juan no se encuentra registrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void registrarPacienteTest() {
        Paciente paciente1 = new Paciente(1, "Juan", "Gomez", null);

        registroDePacientesService.registrarPaciente(paciente1);

        Mockito.verify(contenedorMemoria.getPacienteDao(), Mockito.times(1)).registrar(paciente1);
    }

    @Test
    public void seleccionarObraSocialNoHayObraSocialTest() {
        Mockito.when(contenedorMemoria.getObraSocialDao().listarTodos())
                .thenReturn(Arrays.asList());
        try {
            registroDePacientesService.seleccionarObraSocial();
        } catch (IllegalStateException e) {
            assertEquals("No hay obras sociales disponibles.", e.getMessage());
        }
    }

    @Test
    public void seleccionarObraSocialTest() {
        ObraSocial osde = new ObraSocial(1, "OSDE");
        ObraSocial swissMedical = new ObraSocial(2, "Swiss Medical");

        Mockito.when(contenedorMemoria.getObraSocialDao().listarTodos())
                .thenReturn(Arrays.asList(osde, swissMedical));

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        ObraSocial resultado = registroDePacientesService.seleccionarObraSocial();

        assertEquals(osde, resultado);
    }

    @Test
    public void seleccionarObraSocialInputNoIntTest() {
        ObraSocial osde = new ObraSocial(1, "OSDE");
        ObraSocial swissMedical = new ObraSocial(2, "Swiss Medical");

        Mockito.when(contenedorMemoria.getObraSocialDao().listarTodos())
                .thenReturn(Arrays.asList(osde, swissMedical));

        ByteArrayInputStream in = new ByteArrayInputStream("asd\n1\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        registroDePacientesService.seleccionarObraSocial();

        assertTrue(out.toString().contains("Por favor, ingrese un número válido."));
        assertTrue(out.toString().contains("Por favor, ingrese el número de la obra social que posee:"));
    }

    @Test
    public void getInstanciaTest() {
        RegistroDePacientesService instancia1 = RegistroDePacientesService.getInstancia(contenedorMemoria);
        RegistroDePacientesService instancia2 = RegistroDePacientesService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }
}