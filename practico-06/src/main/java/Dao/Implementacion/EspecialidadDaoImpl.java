package Dao.Implementacion;

import Dao.Interfaces.EspecialidadDAO;
import Entidades.Especialidad;

import java.util.*;

public class EspecialidadDaoImpl implements EspecialidadDAO {
        private Map<Integer, Especialidad> especialidades =new HashMap<>();

    @Override
    public List<Especialidad> listarTodos() {
        return new ArrayList<>(especialidades.values());
    }

    @Override
    public Especialidad buscarPorId(int id) {
        return especialidades.get(id);
    }

    @Override
    public Especialidad buscarPorNombre(String nombre) {
        for (Especialidad especialidad : especialidades.values()) {
            if (especialidad.getNombre().equals(nombre)) {
                return especialidad;
            }
        }
        throw new NoSuchElementException("No hay " + nombre + ".");
    }

    @Override
    public void registrar(Especialidad especialidad) {
        especialidades.put(especialidad.getId(), especialidad);
    }

    @Override
    public void elimnar(int id) {
       especialidades.remove(id);
    }

    @Override
    public void modificar(Especialidad especialidad) {
        if (especialidades.containsKey(especialidad.getId())) {
            especialidades.put(especialidad.getId(), especialidad);
        } else {
            throw new IllegalArgumentException("La especialidad con id " + especialidad.getId() + " no existe.");
        }
    }
}
