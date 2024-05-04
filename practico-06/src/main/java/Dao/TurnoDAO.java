package Dao;

import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Turno;

public interface TurnoDAO extends DAO<Turno>{
    Turno buscarPorMedico(Medico medico);

    Turno buscarPorPaciente(Paciente paciente);

    Turno buscarPorEstado(String EstadoTurno);

    Turno buscarPorParticular(Boolean particular);
}
