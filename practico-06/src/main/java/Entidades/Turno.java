package Entidades;

import Interfaces.TurnoDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turno implements TurnoDAO {
    private Paciente paciente;
    private Medico medico;

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
