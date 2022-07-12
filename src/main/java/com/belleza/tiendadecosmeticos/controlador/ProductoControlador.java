package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.dto.ProductoDto;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.servicio.Impl.ProductoServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/api/producto")
public class ProductoControlador {

    @Autowired
    private ProductoServicioImpl productoServicio;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        return productoServicio.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id){
        return productoServicio.productoPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> guardarProductos(@RequestBody ProductoDto productoDto){
        return productoServicio.guardarProducto(productoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto, @PathVariable Long id){
        return productoServicio.actualizarProducto(producto,id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProductos(@PathVariable Long id){
        productoServicio.eliminarProducto(id);
    }

}
