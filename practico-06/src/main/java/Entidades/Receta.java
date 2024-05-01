package Entidades;

import Interfaces.RecetaDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receta implements RecetaDAO {
    private List<Medicamento> medicamentos;
    private Medico medico;

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
