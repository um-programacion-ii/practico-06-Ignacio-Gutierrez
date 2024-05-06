package Dao.Implementacion;

import Dao.Interfaces.MedicoDAO;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.ObraSocial;

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
    public List<Medico> buscarPorEspecialidad(Especialidad especialidad) {
        List<Medico> medicosConEspecialidad = new ArrayList<>();
        for (Medico medico : medicos.values()) {
            if (medico.getEspecialidad().equals(especialidad)) {
                medicosConEspecialidad.add(medico);
            }
        }
        if (medicosConEspecialidad.isEmpty()) {
            throw new NoSuchElementException("No existe " + especialidad.getNombre() + ".");
        }
        return medicosConEspecialidad;
    }

    @Override
    public List<Medico> buscarPorObraSocial(ObraSocial obraSocial) {
        List<Medico> medicosConObraSocial = new ArrayList<>();
        for (Medico medico : medicos.values()) {
            if (medico.getObrasSocialesAceptadas().contains(obraSocial)) {
                medicosConObraSocial.add(medico);
            }
        }
        if (medicosConObraSocial.isEmpty()) {
            throw new NoSuchElementException("No existe " + obraSocial.getNombre() + ".");
        }
        return medicosConObraSocial;
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
