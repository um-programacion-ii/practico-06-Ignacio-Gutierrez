package Entidades;

import Dao.Implementacion.MedicamentoDaoImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Farmacia {
    private MedicamentoDaoImpl medicamentoDaoImpl;
    private Drogueria drogueria;

    public void solicitarMedicamentoPorId(int id, int cantidadNecesaria) {
        try {
            medicamentoDaoImpl.retirarCantidadPorId(id, cantidadNecesaria);
        } catch (NoSuchElementException e) {
            drogueria.solicitarMedicamentoPorId(id);
        }
    }

    public void solicitarMedicamentoPorNombre(String nombre, int cantidadNecesaria) {
        try {
            medicamentoDaoImpl.retirarCantidadPorNombre(nombre, cantidadNecesaria);
        } catch (NoSuchElementException e) {
            drogueria.solicitarMedicamentoPorNombre(nombre);
        }
    }

}