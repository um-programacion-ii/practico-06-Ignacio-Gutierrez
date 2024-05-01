package Entidades;

import Interfaces.ObraSocialDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObraSocial implements ObraSocialDAO {
    private String nombre;

    @Override
    public List<ObraSocial> listarTodos() {
        return List.of();
    }

    @Override
    public ObraSocial buscarPorId(int id) {
        return null;
    }

    @Override
    public void registrar(ObraSocial obraSocial) {

    }

    @Override
    public void elimnar(int id) {

    }

    @Override
    public void modificar(ObraSocial obraSocial) {

    }
}
