package com.belleza.tiendadecosmeticos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {

    /*
     * Un Dto, muy practicos para filtrar o evitar que informacion sensible
     * quede visible.
     * Este Dto lo utilizo para capturar diferente informacion, como las propiedades del
     * productos y con que categoria se va a relacionar, para guardarla en 2 tablas distintas.
     * */

    @Size(min = 3, max = 35, message = "El nombre del producto debe contener más de 3 letras y menos de 35")
    private String nombre;

    //@Size(min = 1, message = "El precio del producto no puede ser 0")
    @Min(value = 1, message = "El precio del producto no puede ser 0")
    private int precio;

    //@Size(min = 1, message = "La cantidad ingresada no puede ser 0")
    @Min(value = 1, message = "La cantidad ingresada no puede ser 0")
    private int cantidad;

    @Size(min = 3, message = "El nombre del color debe ser válido")
    @Pattern(regexp = "[a-zA-Z]+", message = "El color no puede contener números")
    private String color;

    private Long categoriaId;

}
