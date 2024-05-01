package Entidades;

import Interfaces.MedicamentoDAO;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento implements MedicamentoDAO {
    private String nombre;
    private int cantidad;

    @Override
    public List<Medicamento> listarTodos() {
        return List.of();
    }

    @Override
    public Medicamento buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Medicamento medicamento) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Medicamento medicamento) {

    }
}
