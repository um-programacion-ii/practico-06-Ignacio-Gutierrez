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
    public List<Turno> buscarPorPaciente(Paciente paciente) {
        List<Turno> turnoPorPaciente = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getPaciente().equals(paciente)) {
                turnoPorPaciente.add(turno);
            }
        }
        if (turnoPorPaciente.isEmpty()) {
            throw new NoSuchElementException("No existe " + paciente.getNombre() + ".");
        }
        return turnoPorPaciente;
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        List<Turno> turnoPorMedico = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getMedico().equals(medico)) {
                turnoPorMedico.add(turno);
            }
        }
        if (turnoPorMedico.isEmpty()) {
            throw new NoSuchElementException("No existe " + medico.getNombre() + ".");
        }
        return turnoPorMedico;
    }

    @Override
    public List<Turno> buscarPorEstado(String EstadoTurno) {
        List<Turno> turnoPorEstado = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getEstadoTurno().equals(EstadoTurno)) {
                turnoPorEstado.add(turno);
            }
        }
        if (turnoPorEstado.isEmpty()) {
            throw new NoSuchElementException("No hay turnos " + EstadoTurno + ".");
        }
        return turnoPorEstado;
    }

    @Override
    public List<Turno> buscarPorParticular(Boolean particular) {
        List<Turno> turnoPorParticular = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getParticular().equals(particular)) {
                turnoPorParticular.add(turno);
            }
        }
        if (turnoPorParticular.isEmpty()) {
            throw new NoSuchElementException("No hay turnos " + particular + ".");
        }
        return turnoPorParticular;
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
