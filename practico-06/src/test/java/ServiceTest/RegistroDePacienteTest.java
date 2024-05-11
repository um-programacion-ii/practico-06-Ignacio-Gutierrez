package ServiceTest;

import Dao.Implementacion.PacienteDaoImpl;
import Dao.Interfaces.EspecialidadDAO;
import Dao.Interfaces.ObraSocialDAO;
import Dao.Interfaces.PacienteDAO;
import Entidades.ContenedorMemoria;
import Entidades.Especialidad;
import Entidades.ObraSocial;
import Entidades.Paciente;
import Servicios.RegistroDePacientesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
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
        registroDePacientesService = RegistroDePacientesService.getInstancia(contenedorMemoria);
    }

    @Test
    public void buscarPacienteNoExisteTest() {
        Paciente paciente1 = new Paciente(1, "Juan", "Gomez", null);
        Paciente paciente2 = new Paciente(2, "Nicolas", "Perez", null);
        //Paciente paciente3 = new Paciente(1, "Juan", "Perez", null);

        registroDePacientesService.registrarPaciente(paciente1);
        registroDePacientesService.registrarPaciente(paciente2);
        //registroDePacientesService.registrarPaciente(paciente3);

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
    public void buscarPacienteExceptionTest() {
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorNombre("Juan")).thenThrow(new NoSuchElementException("No existe Juan."));
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorApellido("Perez")).thenThrow(new NoSuchElementException("No existe Perez."));

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

        registroDePacientesService.registrarPaciente(paciente1);
        registroDePacientesService.registrarPaciente(paciente2);
        registroDePacientesService.registrarPaciente(paciente3);

        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorNombre("Juan")).thenReturn(Arrays.asList(paciente1, paciente3));
        Mockito.when(contenedorMemoria.getPacienteDao().buscarPorApellido("Perez")).thenReturn(Arrays.asList(paciente2, paciente3));

        Paciente resultado = registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");

        assertEquals(paciente3, resultado);
    }

    @Test
    public void seleccionarObraSocialTest() {
        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial sanCorSalud = new ObraSocial(2,"SanCor Salud");
        ObraSocial swissMedical = new ObraSocial(3,"Swiss Medical");

        List<ObraSocial> todasLasObrasSociales = Arrays.asList(osde, sanCorSalud, swissMedical);

        Mockito.when(contenedorMemoria.getObraSocialDao().listarTodos()).thenReturn(todasLasObrasSociales);

        InputStream in = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(in);

        Mockito.verify(Mockito.mock(ObraSocialDAO.class), Mockito.times(0)).registrar(Mockito.any(ObraSocial.class));
    }

    @Test
    public void getInstanciaTest() {
        RegistroDePacientesService instancia1 = RegistroDePacientesService.getInstancia(contenedorMemoria);
        RegistroDePacientesService instancia2 = RegistroDePacientesService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }
}