package Entidades;

import Interfaces.PacienteDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente implements PacienteDAO {
    private String nombre;
    private String apellido;
    private String obraSocial;

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
