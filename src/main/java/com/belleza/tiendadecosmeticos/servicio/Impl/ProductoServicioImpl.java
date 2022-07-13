package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.ProductoRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.exceptions.DataBaseException;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.modelo.ProductosCategorias;
import com.belleza.tiendadecosmeticos.repositorio.ProductosCategoriasRepositorio;
import com.belleza.tiendadecosmeticos.repositorio.ProductosRepositorio;
import com.belleza.tiendadecosmeticos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductosRepositorio productosRepositorio;

    @Autowired
    private ProductosCategoriasRepositorio productosCategoriasRepositorio;

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        List<Producto> productos = productosRepositorio.findAll();
        List<ProductoResponseDTO> productoResponseDTOS = new ArrayList<>();

        if (productos.isEmpty()) {
            return Collections.emptyList();
        } else {
            productos.parallelStream().forEach(producto -> {
                productoResponseDTOS.add(new ProductoResponseDTO(producto.getId(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        producto.getCantidad(),
                        producto.getColor()));
            });

            return productoResponseDTOS;
        }

    }

    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO productoRequestDto) {


        Producto producto = new Producto();
        producto.setNombre(productoRequestDto.getNombre());
        producto.setPrecio(productoRequestDto.getPrecio());
        producto.setCantidad(productoRequestDto.getCantidad());
        producto.setColor(productoRequestDto.getColor());
        Producto nuevoProducto = productosRepositorio.save(producto);

        ProductosCategorias productosCategorias = new ProductosCategorias();
        productosCategorias.setCategoria_id(productoRequestDto.getCategoriaId());
        productosCategorias.setProducto_id(producto.getId());
        productosCategoriasRepositorio.save(productosCategorias);

        if (nuevoProducto != null) {
            return new ProductoResponseDTO(nuevoProducto.getId(),
                    nuevoProducto.getNombre(),
                    nuevoProducto.getPrecio(),
                    nuevoProducto.getCantidad(),
                    nuevoProducto.getColor());
        } else {
            throw new DataBaseException("Hubo un error interno al guardar el producto [" + productoRequestDto.getNombre() + "], intente nuevamente");
        }
    }

    @Override
    public ResponseInfoDTO eliminarProducto(Long id, HttpServletRequest httpServletRequest) {

        productosCategoriasRepositorio.deleteById(id);//TODO Arreglar esto. El ID de la tabla intermedia no es el mismo ID que el del producto en sí
        productosRepositorio.deleteById(id);

        if (productosRepositorio.findById(id).orElse(null) == null) {
            return new ResponseInfoDTO("Producto con la Id [" + id + "] eliminado exitosamente",
                    httpServletRequest.getServletPath(),
                    HttpStatus.OK.value());
        } else {
            throw new DataBaseException("Hubo un error interno al borrar el producto con ID ["+ id +"], intente nuevamente");
        }

    }

    @Override
    public ProductoResponseDTO productoPorId(Long id) {

        Producto producto = productosRepositorio.findById(id).orElse(null);

        if (producto == null) {
            throw new EntityNotFoundException("El producto con la ID [" + id + "] no se encuentra en la base de datos");
        } else {
            return new ProductoResponseDTO(producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getCantidad(),
                    producto.getColor());
        }
    }

    @Override
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO productoRequestDto, Long id) {

        Producto productoActual = productosRepositorio.findById(id).orElse(null);

        if (productoActual != null) {
            productoActual.setId(id);
            productoActual.setNombre(productoRequestDto.getNombre());
            productoActual.setPrecio(productoRequestDto.getPrecio());
            productoActual.setCantidad(productoRequestDto.getCantidad());
            productoActual.setColor(productoRequestDto.getColor());
            productosRepositorio.save(productoActual);
            return new ProductoResponseDTO(productoActual.getId(),
                    productoActual.getNombre(),
                    productoActual.getPrecio(),
                    productoActual.getCantidad(),
                    productoActual.getColor());

        } else {
            throw new EntityNotFoundException("El producto con la ID [" + id + "] no se encuentra en la base de datos");
        }

    }
}
