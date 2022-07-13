package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.CategoriaRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.CategoriaResponseDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.exceptions.DataBaseException;
import com.belleza.tiendadecosmeticos.modelo.Categoria;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.repositorio.CategoriaRepositorio;
import com.belleza.tiendadecosmeticos.servicio.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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

        List<Categoria> categorias = categoriaRepositorio.findAll();
        List<CategoriaResponseDTO> categoriaResponseDTOS = new ArrayList<>();

        if (categorias.isEmpty()) {
            return Collections.emptyList();
        } else {
            categorias.parallelStream().forEach(categoria -> {
                categoriaResponseDTOS.add(new CategoriaResponseDTO(categoria.getId(),
                        categoria.getNombre()));
            });

            return categoriaResponseDTOS;
        }

    }

    @Override
    public CategoriaResponseDTO guardarCategorias(CategoriaRequestDTO categoriaRequestDTO) {

        Categoria categoriaPorCrear = new Categoria();
        categoriaPorCrear.setNombre(categoriaRequestDTO.getNombre());

        Categoria nuevaCategoria = categoriaRepositorio.save(categoriaPorCrear);

        if (nuevaCategoria == null) {
            throw new DataBaseException("Hubo un error interno al guardar la categoría [" + categoriaRequestDTO.getNombre() + "], intente nuevamente");
        } else {
            return new CategoriaResponseDTO(nuevaCategoria.getId(),
                    nuevaCategoria.getNombre());
        }

    }

    @Override
    public ResponseInfoDTO eliminarCategoria(Long id, HttpServletRequest httpServletRequest) {
        categoriaRepositorio.deleteById(id);

        if (categoriaRepositorio.findById(id).orElse(null) == null) {
            return new ResponseInfoDTO("Categoria con la Id [" + id + "] eliminada exitosamente",
                    httpServletRequest.getServletPath(),
                    HttpStatus.OK.value());

        } else {
            throw new DataBaseException("Hubo un error interno al borrar la categoría con ID ["+ id +"], intente nuevamente");
        }

    }

    @Override
    public List<ProductoResponseDTO> listarProductoPorCategoria(Long id) {
        Categoria categoria = categoriaRepositorio.findById(id).orElse(null);

        if (categoria != null) {
            Set<Producto> productosEnCategoria = categoria.getProductos();
            List<ProductoResponseDTO> productoEnCategoriaResponseDTOS = new ArrayList<>();

            productosEnCategoria.parallelStream().forEach(producto -> {
                productoEnCategoriaResponseDTOS.add(new ProductoResponseDTO(producto.getId(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        producto.getCantidad(),
                        producto.getColor()));
            });

            return productoEnCategoriaResponseDTOS;
        } else {
            throw new EntityNotFoundException("La categoría con la ID [" + id + "] no se encuentra en la base de datos");
        }
    }
}
