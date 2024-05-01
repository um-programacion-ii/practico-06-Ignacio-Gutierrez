package Entidades;

import Dao.ObraSocialDAO;

import java.util.List;

public class ObraSocialDaoImpl implements ObraSocialDAO {

    @Override
    public List<ObraSocial> listarTodos() {
        return List.of();
    }

    @Override
    public ObraSocial buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(ObraSocial obraSocial) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(ObraSocial obraSocial) {

    }
}
