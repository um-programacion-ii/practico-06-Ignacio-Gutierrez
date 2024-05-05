package Dao.Implementacion;

import Dao.Interfaces.TurnoDAO;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Turno;

import java.util.*;

public class TurnoDaoImpl implements TurnoDAO {
    private Map<Integer, Turno> turnos = new HashMap<>();

    @Override
    public List<Turno> listarTodos() {
        return new ArrayList<>(turnos.values());
    }

    @Override
    public Turno buscarPorId(int id) {
        return turnos.get(id);
    }

    @Override
    public Turno buscarPorPaciente(Paciente paciente) {
        for (Turno turno : turnos.values()) {
            if (turno.getPaciente().equals(paciente)) {
                return turno;
            }
        }
        throw new NoSuchElementException("No existe " + paciente.getNombre() + ".");
    }

    @Override
    public Turno buscarPorMedico(Medico medico) {
        for (Turno turno : turnos.values()) {
            if (turno.getMedico().equals(medico)) {
                return turno;
            }
        }
        throw new NoSuchElementException("No existe " + medico.getNombre() + ".");
    }

    @Override
    public Turno buscarPorEstado(String EstadoTurno) {
        for (Turno turno : turnos.values()) {
            if (turno.getEstadoTurno().equals(EstadoTurno)) {
                return turno;
            }
        }
        throw new NoSuchElementException("No hay turnos " + EstadoTurno + ".");
    }

    @Override
    public Turno buscarPorParticular(Boolean particular) {
        for (Turno turno : turnos.values()) {
            if (turno.getParticular().equals(particular)) {
                return turno;
            }
        }
        throw new NoSuchElementException("No hay turnos " + particular + ".");
    }

    @Override
    public void registrar(Turno turno) {
        turnos.put(turno.getId(), turno);
    }

    @Override
    public void elimnar(int id) {
        turnos.remove(id);
    }

    @Override
    public void modificar(Turno turno) {
        if (turnos.containsKey(turno.getId())) {
            turnos.put(turno.getId(), turno);
        } else {
            throw new NoSuchElementException("El turno con id " + turno.getId() + " no existe.");
        }
    }
}
