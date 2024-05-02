package Entidades;

import Dao.MedicoDAO;

import java.util.*;

public class MedicoDaoImpl implements MedicoDAO {
    private Map<Integer, Medico> medicos = new HashMap<>();

    @Override
    public List<Medico> listarTodos() {
        return new ArrayList<>(medicos.values());
    }

    @Override
    public Medico buscarPorId(int id) {
        return medicos.get(id);
    }

    @Override
    public Medico buscarPorNombre(String nombre) {
        for (Medico medico : medicos.values()) {
            if (medico.getNombre().equals(nombre)) {
                return medico;
            }
        }
        throw new NoSuchElementException("No exite " + nombre + ".");
    }

    @Override
    public Medico buscarPorApellido(String apellido) {
        for (Medico medico : medicos.values()) {
            if (medico.getApellido().equals(apellido)) {
                return medico;
            }
        }
        throw new NoSuchElementException("No exite " + apellido + ".");
    }

    @Override
    public Medico buscarPorEspecialidad(Especialidad especialidad) {
        for (Medico medico : medicos.values()) {
            if (medico.getEspecialidad().equals(especialidad)) {
                return medico;
            }
        }
        throw new NoSuchElementException("No exite " + especialidad + ".");
    }

    @Override
    public Medico buscarPorObraSocial(ObraSocial obraSocial) {
        for (Medico medico : medicos.values()) {
            if (medico.getObraSocial().equals(obraSocial)) {
                return medico;
            }
        }
        throw new NoSuchElementException("No exite " + obraSocial + ".");
    }

    @Override
    public void registrar(Medico medico) {
        medicos.put(medico.getId(), medico);
    }

    @Override
    public void elimnar(int id) {
        medicos.remove(id);
    }

    @Override
    public void modificar(Medico medico) {
        if (medicos.containsKey(medico.getId())) {
            medicos.put(medico.getId(), medico);
        } else {
            throw new IllegalArgumentException("El medico con id " + medico.getId() + " no existe.");
        }
    }

}
