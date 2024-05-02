package Dao;

import Entidades.Medicamento;

public interface MedicamentoDAO extends DAO<Medicamento>{

    Medicamento buscarPorNombre(String nombre);

    void retirarCantidadPorId(int id, int cantidad); // Nuevo método

    void retirarCantidadPorNombre(String nombre, int cantidad);

    void agregarCantidadPorId(int id, int cantidad);

    void agregarCantidadPorNombre(String nombre, int cantidad);

}
