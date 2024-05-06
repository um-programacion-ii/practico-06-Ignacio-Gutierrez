package ServiceTest;

import Dao.Implementacion.PacienteDaoImpl;
import Entidades.ContenedorMemoria;
import Entidades.Paciente;
import Servicios.RegistroDePacientesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.mockito.Mockito;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class RegistroDePacienteTest {
    private PacienteDaoImpl pacienteDaoImpl;
    private RegistroDePacientesService registroDePacientesService;
    private ContenedorMemoria contenedorMemoria;

    @BeforeEach
    public void setUp() {
        pacienteDaoImpl = Mockito.mock(PacienteDaoImpl.class);
        contenedorMemoria = Mockito.mock(ContenedorMemoria.class);
        Mockito.when(contenedorMemoria.getPacienteDao()).thenReturn(pacienteDaoImpl);
        registroDePacientesService = new RegistroDePacientesService(contenedorMemoria);
    }

    @Test
    public void buscarPacienteNoExisteTest() {
        Paciente paciente1 = new Paciente(1, "Juan", "Gomez", null);
        Paciente paciente2 = new Paciente(2, "Nicolas", "Perez", null);
        //Paciente paciente3 = new Paciente(1, "Juan", "Perez", null);

        pacienteDaoImpl.registrar(paciente1);
        pacienteDaoImpl.registrar(paciente2);
        //pacienteDaoImpl.registrar(paciente3);

        Mockito.when(pacienteDaoImpl.buscarPorNombre("Juan")).thenReturn(Arrays.asList(paciente1));
        Mockito.when(pacienteDaoImpl.buscarPorApellido("Perez")).thenReturn(Arrays.asList(paciente2));

        try {
            registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");
        } catch (NoSuchElementException e) {
            assertEquals("Perez, Juan no se encuentra registrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void buscarPacienteListaVaciaTest() {
        Mockito.when(pacienteDaoImpl.buscarPorNombre("Juan")).thenReturn(Arrays.asList());
        Mockito.when(pacienteDaoImpl.buscarPorApellido("Perez")).thenReturn(Arrays.asList());

        try {
            registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");
        } catch (NoSuchElementException e) {
            assertEquals("Perez, Juan no se encuentra registrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void buscarPacienteExceptionTest() {
        Mockito.when(pacienteDaoImpl.buscarPorNombre("Juan")).thenThrow(new NoSuchElementException("No existe Juan."));
        Mockito.when(pacienteDaoImpl.buscarPorApellido("Perez")).thenThrow(new NoSuchElementException("No existe Perez."));

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

        pacienteDaoImpl.registrar(paciente1);
        pacienteDaoImpl.registrar(paciente2);
        pacienteDaoImpl.registrar(paciente3);

        Mockito.when(pacienteDaoImpl.buscarPorNombre("Juan")).thenReturn(Arrays.asList(paciente1, paciente3));
        Mockito.when(pacienteDaoImpl.buscarPorApellido("Perez")).thenReturn(Arrays.asList(paciente2, paciente3));

        Paciente resultado = registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");

        assertEquals(paciente3, resultado);
    }

    @Test
    public void registrarPacienteTest() {
        Paciente pacienteNuevo = new Paciente(1, "Juan", "Perez", null);

        registroDePacientesService.registrarPaciente(pacienteNuevo);

        Mockito.verify(pacienteDaoImpl).registrar(pacienteNuevo);

        Mockito.when(pacienteDaoImpl.buscarPorNombre("Juan")).thenReturn(Arrays.asList(pacienteNuevo));
        Mockito.when(pacienteDaoImpl.buscarPorApellido("Perez")).thenReturn(Arrays.asList(pacienteNuevo));
        Paciente pacienteGuardado = registroDePacientesService.buscarPacientePorNombreYApellido("Juan", "Perez");

        assertEquals(pacienteNuevo, pacienteGuardado);
    }

    @Test
    public void getInstanciaTest() {
        RegistroDePacientesService instancia1 = RegistroDePacientesService.getInstancia(contenedorMemoria);
        RegistroDePacientesService instancia2 = RegistroDePacientesService.getInstancia(contenedorMemoria);

        assertSame(instancia1, instancia2);
    }
}