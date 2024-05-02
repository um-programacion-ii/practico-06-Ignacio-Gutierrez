package Entidades;

import Dao.PacienteDAO;

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
    public Paciente buscarPorNombre(String nombre) {
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getNombre().equals(nombre)) {
                return paciente;
            }
        }
        throw new NoSuchElementException("No existe " + nombre + ".");
    }

    @Override
    public Paciente buscarPorApellido(String apellido) {
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getApellido().equals(apellido)) {
                return paciente;
            }
        }
        throw new NoSuchElementException("No existe " + apellido + ".");
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
