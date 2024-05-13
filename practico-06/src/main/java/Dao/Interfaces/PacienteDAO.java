package Dao.Interfaces;

import Entidades.ObraSocial;
import Entidades.Paciente;

import java.util.List;

public interface PacienteDAO extends DAO<Paciente>{
    List<Paciente> buscarPorNombre(String nombre);

    List<Paciente> buscarPorApellido(String apellido);

    List<Paciente> buscarPorObraSocial(ObraSocial obraSocial);
}
