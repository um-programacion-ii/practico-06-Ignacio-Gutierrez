package Dao;

import Entidades.Especialidad;
import Entidades.Medicamento;
import Entidades.Medico;
import Entidades.ObraSocial;

public interface MedicoDAO extends DAO<Medico>{

    Medico buscarPorNombre(String nombre);

    Medico buscarPorApellido(String apellido);

    Medico buscarPorEspecialidad(Especialidad especialidad);

    Medico buscarPorObraSocial(ObraSocial obraSocial);

}
