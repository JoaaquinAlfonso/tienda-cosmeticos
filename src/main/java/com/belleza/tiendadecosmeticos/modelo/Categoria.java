package com.belleza.tiendadecosmeticos.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Etiqueta Data propia de lombok, nos crea los get, set
@Data
//Crea un constructor vacio
@NoArgsConstructor
//Crea un constructor con todo lo que tenemos
@AllArgsConstructor
//Etiqueta que le dice a nuestro programa que esta es nuestra entidad
@Entity
public class Categoria {

    /*
     * Le decimos con la etiqueda Id que est va a tener una llave primaria
     * y que esta se va a autoincrementar.
     * Ademas colocamos un nombre a esta columna, el cual lo vamos a necesitar
     * como referencia en nuestra relacion OneToMany
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long id;

    private String nombre;


    /*
     * Utilizamos una relacion OneToMany para referirnos que una categoria puede tener
     * muchos productos.
     * Creamos nuestra tabla intermedia, la cual va tener nuestra relacion y le damos los
     * nombres de los campos que se van a relacionar en esta tabla.
     * */
    @OneToMany
    @JsonBackReference
    @JoinTable(name = "ProductosCategorias", joinColumns = @JoinColumn(name = "categoria_id",
            referencedColumnName = "categoria_id"), inverseJoinColumns = @JoinColumn(name = "producto_id",
            referencedColumnName = "producto_id"))
    private Set<Producto> productos = new HashSet<>();


}
