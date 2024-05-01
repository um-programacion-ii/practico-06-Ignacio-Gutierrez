package Entidades;

import Dao.RecetaDAO;

import java.util.List;

public class RecetaDaoImpl implements RecetaDAO {
    @Override
    public List<Receta> listarTodos() {
        return List.of();
    }

    @Override
    public Receta buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Receta receta) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Receta receta) {

    }
}
