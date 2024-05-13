package Dao.Interfaces;

import Entidades.ObraSocial;

public interface ObraSocialDAO extends DAO<ObraSocial>{
    ObraSocial buscarPorNombre(String nombre);
}
