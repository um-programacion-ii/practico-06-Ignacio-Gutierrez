package Dao.Implementacion;

import Dao.Interfaces.ObraSocialDAO;
import Entidades.ObraSocial;

import java.util.*;

public class ObraSocialDaoImpl implements ObraSocialDAO {
    private Map<Integer, ObraSocial> obrasSociales =new HashMap<>();

    @Override
    public List<ObraSocial> listarTodos() {
        return new ArrayList<>(obrasSociales.values());
    }

    @Override
    public ObraSocial buscarPorId(int id) {
        return obrasSociales.get(id);
    }

    @Override
    public ObraSocial buscarPorNombre(String nombre) {
        for (ObraSocial obraSocial : obrasSociales.values()) {
            if (obraSocial.getNombre().equals(nombre)) {
                return obraSocial;
            }
        }
        throw new NoSuchElementException("No hay " + nombre + ".");
    }

    @Override
    public void registrar(ObraSocial obraSocial) {
        obrasSociales.put(obraSocial.getId(), obraSocial);
    }

    @Override
    public void elimnar(int id) {
        obrasSociales.remove(id);
    }

    @Override
    public void modificar(ObraSocial obraSocial) {
        if (obrasSociales.containsKey(obraSocial.getId())) {
            obrasSociales.put(obraSocial.getId(), obraSocial);
        } else {
            throw new IllegalArgumentException("La obrasocial con id " + obraSocial.getId() + " no existe.");
        }
    }
}
