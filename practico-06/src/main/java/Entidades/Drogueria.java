package Entidades;

public class Drogueria  {
    public static Medicamento solicitarMedicamento(Medicamento medicamento) {
        return new Medicamento(medicamento.getId(), medicamento.getNombre(), 500);
    }
}
