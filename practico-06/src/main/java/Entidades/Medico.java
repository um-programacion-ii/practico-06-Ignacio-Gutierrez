package Entidades;

import Interfaces.MedicoDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico implements MedicoDAO {
    private String nombre;
    private String apellido;
    private Especialidad especialidad;
    private ObraSocial obraSocial;

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
