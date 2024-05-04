package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    private int id;
    private String nombre;
    private String apellido;
    private Especialidad especialidad;
    private List<ObraSocial> obrasSocialesAceptadas;
}
