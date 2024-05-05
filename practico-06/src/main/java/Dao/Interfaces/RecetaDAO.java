package Dao.Interfaces;

import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Receta;

public interface RecetaDAO extends DAO<Receta>{
    Receta buscarPorMedico(Medico medico);

    Receta buscarPorPaciente(Paciente paciente);
}
