package com.belleza.tiendadecosmeticos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaRequestDTO {

    @Size(min = 3, max = 20, message = "La categoría debe contener más de 3 letras y menos de 20")
    @Pattern(regexp = "[a-zA-Z]+", message = "La categoría no puede contener números")
    private String nombre;
}
