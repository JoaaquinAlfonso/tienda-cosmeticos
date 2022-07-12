package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.modelo.Categoria;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.repositorio.CategoriaRepositorio;
import com.belleza.tiendadecosmeticos.servicio.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    /*
    * Creamos una clase para implementar nuestros metodos antes creados,
    * le agregamos la etiqueta service y le decimos que este es un implementeacion de
    * nuestra interfas anterior creada, este nos importara todos los metodos, y aqui es
    * donde realizamos toda la logica.
    * */
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    public ResponseEntity<List<Categoria>> listarCategorias() {
        try {
            List<Categoria> categorias = categoriaRepositorio.findAll();
            if (categorias.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categorias);
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Categoria> guardarCategorias(Categoria categoria) {
        try {
            Categoria nuevaCatgoria = categoriaRepositorio.save(categoria);
            if (nuevaCatgoria == null){
                return ResponseEntity.notFound().build();
            }else{
                return ResponseEntity.ok(categoria);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Categoria> eliminarCategoria(Long id) {
        try {
            categoriaRepositorio.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Collection<Producto>> listarProductoPorCategoria(Long id) {
        Categoria categoria = categoriaRepositorio.findById(id).orElseThrow();

        if (categoria != null){
            return new ResponseEntity<>(categoria.getProductos(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
}
