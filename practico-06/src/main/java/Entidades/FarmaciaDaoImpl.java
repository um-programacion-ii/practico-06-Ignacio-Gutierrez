package Entidades;

import Dao.FarmaciaDAO;

import java.util.List;

public class FarmaciaDaoImpl implements FarmaciaDAO {
    @Override
    public List<Farmacia> listarTodos() {
        return List.of();
    }

    @Override
    public Farmacia buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Farmacia farmacia) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Farmacia farmacia) {

    }
}
