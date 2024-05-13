package Dao.Interfaces;

import java.util.List;

public interface DAO<T> {
    List<T> listarTodos();
    T buscarPorId(int id);
    void registrar(T t);
    void elimnar(int id);
    void modificar(T t);
}
