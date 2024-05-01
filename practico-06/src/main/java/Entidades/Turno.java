package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turno {
    private Paciente paciente;
    private Medico medico;
    private ObraSocial obraSocial;
}
