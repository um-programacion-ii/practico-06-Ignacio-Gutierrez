package Entidades;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento {
    private int id;
    private String nombre;
    private int cantidad;
}
