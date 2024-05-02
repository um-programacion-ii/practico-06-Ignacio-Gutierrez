package Entidades;

public class Drogueria  {
    private MedicamentoDaoImpl medicamentoDaoImpl;

    public Drogueria(MedicamentoDaoImpl medicamentoDaoImpl) {
        this.medicamentoDaoImpl = medicamentoDaoImpl;
    }

    public void solicitarMedicamentoPorId(int id) {
        medicamentoDaoImpl.agregarCantidadPorId(id, 500);
    }

    public void solicitarMedicamentoPorNombre(String nombre) {
        medicamentoDaoImpl.agregarCantidadPorNombre(nombre, 500);
    }

}