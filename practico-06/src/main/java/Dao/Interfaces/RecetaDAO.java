package Dao.Interfaces;

import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Receta;

import java.util.List;

public interface RecetaDAO extends DAO<Receta>{
    List<Receta> buscarPorMedico(Medico medico);

    List<Receta> buscarPorPaciente(Paciente paciente);
}
