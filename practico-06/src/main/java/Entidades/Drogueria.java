package Entidades;

public class Drogueria  {
    public Medicamento solicitarMedicamento(Medicamento medicamento) {
        return new Medicamento(medicamento.getId(), medicamento.getNombre(), 500);
    }
}
