package Dao.Interfaces;

import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.ObraSocial;

import java.util.List;

public interface MedicoDAO extends DAO<Medico>{

    List<Medico> buscarPorEspecialidad(Especialidad especialidad);

    List<Medico> buscarPorObraSocial(ObraSocial obraSocial);

}
