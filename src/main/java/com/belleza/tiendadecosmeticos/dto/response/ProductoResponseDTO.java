package com.belleza.tiendadecosmeticos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoResponseDTO {

    private Long id;

    private String nombre;

    private int precio;

    private int cantidad;

    private String color;
}
