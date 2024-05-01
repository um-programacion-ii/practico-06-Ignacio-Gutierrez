package Entidades;

import Dao.EspecialidadDAO;

import java.util.List;

public class EspecialidadDaoImp implements EspecialidadDAO {
    @Override
    public List<Especialidad> listarTodos() {
        return List.of();
    }

    @Override
    public Especialidad buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Especialidad especialidad) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Especialidad especialidad) {

    }
}
