package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.ObjectName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private ObraSocial obraSocial;
}
