package Dao.Implementacion;

import Dao.Interfaces.MedicamentoDAO;
import Entidades.Medicamento;

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
            throw new IllegalArgumentException("El medicamento con id " + medicamento.getId() + " no existe.");
        }
    }

    @Override
    public void retirarCantidadPorId(int id, int cantidad) {
        Medicamento medicamento = medicamentos.get(id);
        if (medicamento != null) {
            int cantidadActual = medicamento.getCantidad();
            if (cantidadActual >= cantidad) {
                medicamento.setCantidad(cantidadActual - cantidad);
            } else {
                throw new IllegalArgumentException("La cantidad a retirar es mayor que la cantidad disponible.");
            }
        } else {
            throw new NoSuchElementException("No se encontró ningún medicamento con el ID especificado.");
        }
    }

    @Override
    public void retirarCantidadPorNombre(String nombre, int cantidad) {
        Medicamento medicamento = buscarPorNombre(nombre);
        if (medicamento != null) {
            int cantidadActual = medicamento.getCantidad();
            if (cantidadActual >= cantidad) {
                medicamento.setCantidad(cantidadActual - cantidad);
            } else {
                throw new IllegalArgumentException("La cantidad a retirar es mayor que la cantidad disponible.");
            }
        }
    }

    @Override
    public void agregarCantidadPorId(int id, int cantidad) {
        Medicamento medicamento = medicamentos.get(id);
        if (medicamento != null) {
            int cantidadActual = medicamento.getCantidad();
            medicamento.setCantidad(cantidadActual + cantidad);
        }
    }

    @Override
    public void agregarCantidadPorNombre(String nombre, int cantidad) {
        Medicamento medicamento = buscarPorNombre(nombre);
        if (medicamento != null) {
            int cantidadActual = medicamento.getCantidad();
            medicamento.setCantidad(cantidadActual + cantidad);
        }
    }

}
