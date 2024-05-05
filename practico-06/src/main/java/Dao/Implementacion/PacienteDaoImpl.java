package Dao.Implementacion;

import Dao.Interfaces.PacienteDAO;
import Entidades.ObraSocial;
import Entidades.Paciente;

import java.util.*;

public class PacienteDaoImpl implements PacienteDAO {
    private Map<Integer, Paciente> pacientes = new HashMap<>();

    @Override
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes.values());
    }

    @Override
    public Paciente buscarPorId(int id) {
        return pacientes.get(id);
    }

    @Override
    public List<Paciente> buscarPorNombre(String nombre) {
        List<Paciente> pacientesConNombre = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getNombre().equals(nombre)) {
                pacientesConNombre.add(paciente);
            }
        }
        if (pacientesConNombre.isEmpty()) {
            throw new NoSuchElementException("No existe " + nombre + ".");
        }
        return pacientesConNombre;
    }

    @Override
    public List<Paciente> buscarPorApellido(String apellido) {
        List<Paciente> pacientesConApellido = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getApellido().equals(apellido)) {
                pacientesConApellido.add(paciente);
            }
        }
        if (pacientesConApellido.isEmpty()) {
            throw new NoSuchElementException("No existe " + apellido + ".");
        }
        return pacientesConApellido;
    }

    @Override
    public Paciente buscarPorObraSocial(ObraSocial obraSocial) {
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getObraSocial().equals(obraSocial)) {
                return paciente;
            }
        }
        throw new NoSuchElementException("No existe " + obraSocial.getNombre() + ".");
    }

    @Override
    public void registrar(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    @Override
    public void elimnar(int id) {
        pacientes.remove(id);
    }

    @Override
    public void modificar(Paciente paciente) {
        if (pacientes.containsKey(paciente.getId())) {
            pacientes.put(paciente.getId(), paciente);
        } else {
            throw new IllegalArgumentException("El paciente con id " + paciente.getId() + " no existe.");
        }
    }

}
