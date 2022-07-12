package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.ProductoDto;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.modelo.ProductosCategorias;
import com.belleza.tiendadecosmeticos.repositorio.ProductosCategoriasRepositorio;
import com.belleza.tiendadecosmeticos.repositorio.ProductosRepositorio;
import com.belleza.tiendadecosmeticos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            if (productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(productos);
        }catch (Exception e){
            System.out.println("ERROR"+e);
        }

        return null;
    }

    @Override
    public ResponseEntity<ProductoDto> guardarProducto(ProductoDto productoDto) {
        try {
            if (productoDto != null){
                Producto producto = new Producto();
                producto.setNombre(productoDto.getNombre());
                producto.setPrecio(productoDto.getPrecio());
                producto.setCantidad(productoDto.getCantidad());
                producto.setColor(productoDto.getColor());
                Producto nuevoProducto = productosRepositorio.save(producto);

                ProductosCategorias productosCategorias = new ProductosCategorias();
                productosCategorias.setCategoria_id(productoDto.getCategoriaId());
                productosCategorias.setProducto_id(producto.getId());
                ProductosCategorias nuevaRelacion = productosCategoriasRepositorio.save(productosCategorias);

                return ResponseEntity.ok(productoDto);

            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;

    }

    @Override
    public ResponseEntity<Producto> eliminarProducto(Long id) {
        try {
            productosCategoriasRepositorio.deleteById(id);
            productosRepositorio.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Producto> productoPorId(Long id) {
        try {
            Producto producto = productosRepositorio.findById(id).orElse(null);
            if (producto == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(producto);
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Producto> actualizarProducto(Producto producto, Long id) {
        try {
            Producto productoActual = productosRepositorio.findById(id).orElseThrow();
            productoActual.setNombre(producto.getNombre());
            productoActual.setPrecio(producto.getPrecio());
            productoActual.setCantidad(producto.getCantidad());
            productoActual.setColor(producto.getColor());
            productosRepositorio.save(producto);
            return  new ResponseEntity<Producto>(HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }
}
