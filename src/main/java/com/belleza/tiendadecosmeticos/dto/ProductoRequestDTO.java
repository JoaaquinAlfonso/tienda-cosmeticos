package com.belleza.tiendadecosmeticos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private  String nombre;

    private int precio;

    private int cantidad;

    private String color;

    private Long categoriaId;

}
