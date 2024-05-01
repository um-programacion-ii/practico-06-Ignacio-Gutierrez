package Entidades;

import Interfaces.DrogueriaDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drogueria implements DrogueriaDAO {
    @Override
    public List<Drogueria> listarTodos() {
        return List.of();
    }

    @Override
    public Drogueria buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Drogueria drogueria) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(Drogueria drogueria) {

    }
}
