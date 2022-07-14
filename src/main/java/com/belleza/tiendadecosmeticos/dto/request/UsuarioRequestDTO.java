package com.belleza.tiendadecosmeticos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioRequestDTO {

    @Size(min = 3, max = 30, message = "El nombre del usuario debe contener más de 3 letras y menos de 30")
    private String nombre;

    //@Size(min = 3, max = 50, message = "La cédula debe contener más de 3 dígitos y menos de 50")
    @Min(value = 1, message = "La cédula debe contener igual o más de 1 dígito y menos de 6")
    @Max(value = 999999, message = "La cédula debe contener igual o más de 1 dígito y menos de 6")
    private int cedula;

    @Size(min = 3, max = 35, message = "La dirección del usuario debe contener más de 3 letras y menos de 35")
    private String direccion;
}
