package Dao.Interfaces;

import Entidades.Especialidad;

public interface EspecialidadDAO extends DAO<Especialidad>{
    Especialidad buscarPorNombre(String nombre);
}
