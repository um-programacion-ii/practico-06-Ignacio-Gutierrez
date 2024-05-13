package Dao.Interfaces;

import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Turno;

import java.util.List;

public interface TurnoDAO extends DAO<Turno>{
    List<Turno> buscarPorMedico(Medico medico);

    List<Turno> buscarPorPaciente(Paciente paciente);

    List<Turno> buscarPorEstado(String EstadoTurno);

    List<Turno> buscarPorParticular(Boolean particular);
}
