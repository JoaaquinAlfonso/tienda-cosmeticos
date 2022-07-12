package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.request.ProductoRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.modelo.ProductosCategorias;
import com.belleza.tiendadecosmeticos.repositorio.ProductosCategoriasRepositorio;
import com.belleza.tiendadecosmeticos.repositorio.ProductosRepositorio;
import com.belleza.tiendadecosmeticos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductosRepositorio productosRepositorio;

    @Autowired
    private ProductosCategoriasRepositorio productosCategoriasRepositorio;

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        try {
            List<Producto> productos = productosRepositorio.findAll();
            List<ProductoResponseDTO> productoResponseDTOS = new ArrayList<>();

            if (productos.isEmpty()) {
                //TODO Hacer que retorne una excepción de lista vacía
                //return ResponseEntity.noContent().build();
            } else {
                productos.parallelStream().forEach(producto -> {
                    productoResponseDTOS.add(new ProductoResponseDTO(producto.getNombre(),
                            producto.getPrecio(),
                            producto.getCantidad(),
                            producto.getColor()));
                });

                return productoResponseDTOS;
            }
        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada o una mejor.
            System.out.println("ERROR" + e);
        }

        return null;
    }

    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO productoRequestDto) {
        try {
            if (productoRequestDto != null) {
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

                return new ProductoResponseDTO(nuevoProducto.getNombre(),
                        nuevoProducto.getPrecio(),
                        nuevoProducto.getCantidad(),
                        nuevoProducto.getColor());

            } else {
                //TODO Agregar lanzamiento de excepción personalizada o una mejor.

                //return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada o una mejor.
            System.out.println(e);
        }

        return null;

    }

    @Override
    public ResponseEntity<Producto> eliminarProducto(Long id) {
        try {
            productosCategoriasRepositorio.deleteById(id);
            productosRepositorio.deleteById(id);
        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada o una mejor.
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ProductoResponseDTO productoPorId(Long id) {
        try {
            Producto producto = productosRepositorio.findById(id).orElse(null);
            if (producto == null) {
                //TODO Agregar lanzamiento de excepción personalizada o una mejor.
                //return ResponseEntity.notFound().build();
            } else {
                return new ProductoResponseDTO(producto.getNombre(),
                        producto.getPrecio(),
                        producto.getCantidad(),
                        producto.getColor());
            }

        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada o una mejor.
            //System.out.println(e);
        }

        return null;
    }

    @Override
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO productoRequestDto, Long id) {
        try {
            Producto productoActual = productosRepositorio.findById(id).orElseThrow();
            productoActual.setId(id);
            productoActual.setNombre(productoRequestDto.getNombre());
            productoActual.setPrecio(productoRequestDto.getPrecio());
            productoActual.setCantidad(productoRequestDto.getCantidad());
            productoActual.setColor(productoRequestDto.getColor());
            productosRepositorio.save(productoActual);
            return new ProductoResponseDTO(productoActual.getNombre(),
                    productoActual.getPrecio(),
                    productoActual.getCantidad(),
                    productoActual.getColor());

        } catch (Exception exception) {
            throw exception;
            //TODO Agregar lanzamiento de excepción personalizada o una mejor.
            //return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }
}
