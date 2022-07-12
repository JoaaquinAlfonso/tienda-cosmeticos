package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.request.CategoriaRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.CategoriaResponseDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Categoria;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.repositorio.CategoriaRepositorio;
import com.belleza.tiendadecosmeticos.servicio.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<CategoriaResponseDTO> listarCategorias() {
        try {
            List<Categoria> categorias = categoriaRepositorio.findAll();
            List<CategoriaResponseDTO> categoriaResponseDTOS = new ArrayList<>();

            if (categorias.isEmpty()) {
                //TODO Agregar lanzamiento de excepción personalizada.
            } else {
                categorias.parallelStream().forEach(categoria -> {
                    categoriaResponseDTOS.add(new CategoriaResponseDTO(categoria.getNombre()));
                });

                return categoriaResponseDTOS;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public CategoriaResponseDTO guardarCategorias(CategoriaRequestDTO categoriaRequestDTO) {
        try {
            Categoria categoriaPorCrear = new Categoria();
            categoriaPorCrear.setNombre(categoriaRequestDTO.getNombre());

            Categoria nuevaCategoria = categoriaRepositorio.save(categoriaPorCrear);

            if (nuevaCategoria == null) {
                //TODO Agregar lanzamiento de excepción personalizada.
                //return ResponseEntity.notFound().build();
            } else {
                return new CategoriaResponseDTO(nuevaCategoria.getNombre());
            }
        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada.
            //System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Categoria> eliminarCategoria(Long id) {
        try {
            categoriaRepositorio.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public List<ProductoResponseDTO> listarProductoPorCategoria(Long id) {
        Categoria categoria = categoriaRepositorio.findById(id).orElseThrow();

        if (categoria != null) {
            Set<Producto> productosEnCategoria = categoria.getProductos();
            List<ProductoResponseDTO> productoEnCategoriaResponseDTOS = new ArrayList<>();

            productosEnCategoria.parallelStream().forEach(producto -> {
                productoEnCategoriaResponseDTOS.add(new ProductoResponseDTO(producto.getNombre(),
                        producto.getPrecio(),
                        producto.getCantidad(),
                        producto.getColor()));
            });

            return productoEnCategoriaResponseDTOS;
        } else {
            //TODO Agregar lanzamiento de excepción personalizada.

            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
