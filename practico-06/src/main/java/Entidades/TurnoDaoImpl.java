package Entidades;

import Dao.TurnoDAO;

import java.util.List;

public class TurnoDaoImpl implements TurnoDAO {
    @Override
    public List<Turno> listarTodos() {
        return List.of();
    }

    @Override
    public Turno buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Turno turno) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Turno turno) {

    }
}
