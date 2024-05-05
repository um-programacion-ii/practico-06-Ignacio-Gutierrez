package Dao.Implementacion;

import Dao.Interfaces.RecetaDAO;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Receta;

import java.util.*;

public class RecetaDaoImpl implements RecetaDAO {
    private Map<Integer, Receta> recetas = new HashMap<>();

    @Override
    public List<Receta> listarTodos() {
        return new ArrayList<>(recetas.values());
    }

    @Override
    public Receta buscarPorId(int id) {
        return recetas.get(id);
    }

    @Override
    public Receta buscarPorPaciente(Paciente paciente) {
        for (Receta receta : recetas.values()) {
            if (receta.getPaciente().equals(paciente)) {
                return receta;
            }
        }
        throw new NoSuchElementException("No existe " + paciente.getNombre() + ".");
    }

    @Override
    public Receta buscarPorMedico(Medico medico) {
        for (Receta receta : recetas.values()) {
            if (receta.getMedico().equals(medico)) {
                return receta;
            }
        }
        throw new NoSuchElementException("No existe " + medico.getNombre() + ".");
    }

    @Override
    public void registrar(Receta receta) {
        recetas.put(receta.getId(), receta);
    }

    @Override
    public void elimnar(int id) {
        recetas.remove(id);
    }

    @Override
    public void modificar(Receta receta) {
        if (recetas.containsKey(receta.getId())) {
            recetas.put(receta.getId(), receta);
        } else {
            throw new NoSuchElementException("La receta con id " + receta.getId() + " no existe.");
        }
    }
}
