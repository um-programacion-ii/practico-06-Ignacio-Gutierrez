package DaoTest;

import Entidades.Drogueria;
import Entidades.Medicamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrogueriaTest {

    @Test
    public void solicitarMedicamentoTest() {
        Medicamento medicamento = new Medicamento(1, "Paracetamol", 0);
        Medicamento medEsperado = new Medicamento(1, "Paracetamol", 500);
        Drogueria drogueria = new Drogueria();
        Medicamento medicamentoSolicitado = drogueria.solicitarMedicamento(medicamento);
        assertEquals(medEsperado, medicamentoSolicitado);
    }
}
