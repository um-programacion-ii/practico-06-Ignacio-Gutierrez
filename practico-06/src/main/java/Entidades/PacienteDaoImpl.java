package Entidades;

import Dao.PacienteDAO;

import java.util.List;

public class PacienteDaoImpl implements PacienteDAO {
    @Override
    public List<Paciente> listarTodos() {
        return List.of();
    }

    @Override
    public Paciente buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Paciente paciente) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Paciente paciente) {

    }
}
