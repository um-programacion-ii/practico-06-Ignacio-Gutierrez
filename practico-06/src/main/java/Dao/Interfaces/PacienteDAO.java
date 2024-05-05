package Dao.Interfaces;

import Entidades.ObraSocial;
import Entidades.Paciente;

public interface PacienteDAO extends DAO<Paciente>{
    Paciente buscarPorNombre(String nombre);

    Paciente buscarPorApellido(String apellido);

    Paciente buscarPorObraSocial(ObraSocial obraSocial);
}
