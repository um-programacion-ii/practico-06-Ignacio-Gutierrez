package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    private String nombre;
    private String apellido;
    private Especialidad especialidad;
    private ObraSocial obraSocial;
}
