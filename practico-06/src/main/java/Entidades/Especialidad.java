package Entidades;

import Interfaces.EspecialidadDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Especialidad implements EspecialidadDAO {
    private String nombre;

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
