package Entidades;

import Dao.MedicamentoDAO;

import java.util.*;

public class MedicamentoDaoImpl implements MedicamentoDAO {
    private Map<Integer, Medicamento> medicamentos = new HashMap<>();

    @Override
    public List<Medicamento> listarTodos() {
        return new ArrayList<>(medicamentos.values());
    }

    @Override
    public Medicamento buscarPorId(int id) {
        return medicamentos.get(id);
    }

    @Override
    public Medicamento buscarPorNombre(String nombre) {
        for (Medicamento medicamento : medicamentos.values()) {
            if (medicamento.getNombre().equals(nombre)) {
                return medicamento;
            }
        }
        throw new NoSuchElementException("No hay " + nombre + ".");
    }

    @Override
    public void registrar(Medicamento medicamento) {
        medicamentos.put(medicamento.getId(), medicamento);
    }

    @Override
    public void elimnar(int id) {
        medicamentos.remove(id);
    }

    @Override
    public void modificar(Medicamento medicamento) {
        if (medicamentos.containsKey(medicamento.getId())) {
            medicamentos.put(medicamento.getId(), medicamento);
        } else {
            throw new IllegalArgumentException("El alumno con id " + medicamento.getId() + " no existe.");
        }
    }
}
