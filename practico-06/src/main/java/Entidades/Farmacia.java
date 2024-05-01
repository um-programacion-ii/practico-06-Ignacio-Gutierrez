package Entidades;

import Interfaces.FarmaciaDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Farmacia implements FarmaciaDAO {
    private List<Medicamento> stock;

    @Override
    public List<Farmacia> listarTodos() {
        return List.of();
    }

    @Override
    public Farmacia buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Farmacia farmacia) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Farmacia farmacia) {

    }
}
