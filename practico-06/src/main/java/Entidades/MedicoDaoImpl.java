package Entidades;

import Dao.MedicoDAO;

import java.util.List;

public class MedicoDaoImpl implements MedicoDAO {
    @Override
    public List<Medico> listarTodos() {
        return List.of();
    }

    @Override
    public Medico buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Medico medico) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Medico medico) {

    }
}
