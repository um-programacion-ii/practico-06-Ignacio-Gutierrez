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
    public List<Receta> buscarPorPaciente(Paciente paciente) {
        List<Receta> recetasPorPaciente = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getPaciente().equals(paciente)) {
                recetasPorPaciente.add(receta);
            }
        }
        if (recetasPorPaciente.isEmpty()) {
            throw new NoSuchElementException("No existe " + paciente.getNombre() + ".");
        }
        return recetasPorPaciente;
    }

    @Override
    public List<Receta> buscarPorMedico(Medico medico) {
        List<Receta> recetasPorMedico = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getMedico().equals(medico)) {
                recetasPorMedico.add(receta);
            }
        }
        if (recetasPorMedico.isEmpty()) {
            throw new NoSuchElementException("No existe " + medico.getNombre() + ".");
        }
        return recetasPorMedico;
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
