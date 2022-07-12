package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.ProductoRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.modelo.ProductosCategorias;
import com.belleza.tiendadecosmeticos.repositorio.ProductosCategoriasRepositorio;
import com.belleza.tiendadecosmeticos.repositorio.ProductosRepositorio;
import com.belleza.tiendadecosmeticos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductosRepositorio productosRepositorio;

    @Autowired
    private ProductosCategoriasRepositorio productosCategoriasRepositorio;

    @Override
    public ResponseEntity<List<Producto>> listarProductos() {
        try {
            List<Producto> productos = productosRepositorio.findAll();
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }

        return null;
    }

    @Override
    public ResponseEntity<ProductoRequestDTO> guardarProducto(ProductoRequestDTO productoRequestDto) {
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
                ProductosCategorias nuevaRelacion = productosCategoriasRepositorio.save(productosCategorias);

                return ResponseEntity.ok(productoRequestDto);

            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
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
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Producto> productoPorId(Long id) {
        try {
            Producto producto = productosRepositorio.findById(id).orElse(null);
            if (producto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            System.out.println(e);
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
