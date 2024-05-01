package Entidades;

import Dao.DrogueriaDAO;

import java.util.List;

public class DrogueriaDaoImpl implements DrogueriaDAO {
    @Override
    public List<Drogueria> listarTodos() {
        return List.of();
    }

    @Override
    public Drogueria buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Drogueria drogueria) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Drogueria drogueria) {

    }
}
