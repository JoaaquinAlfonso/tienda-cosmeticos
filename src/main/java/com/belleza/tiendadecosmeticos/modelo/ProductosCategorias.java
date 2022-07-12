package com.belleza.tiendadecosmeticos.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ProductosCategorias {

    //Esta es nuestra tabla que va a tener la relacion

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long categoria_id;

    private Long producto_id;
}
