package Dao;

import Entidades.Medicamento;

public interface MedicamentoDAO extends DAO<Medicamento>{

    Medicamento buscarPorNombre(String nombre);
}
