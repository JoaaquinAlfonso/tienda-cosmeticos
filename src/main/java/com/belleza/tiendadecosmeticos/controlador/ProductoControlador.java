package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.dto.request.ProductoRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.servicio.Impl.ProductoServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/producto")
public class ProductoControlador {

    @Autowired
    private ProductoServicioImpl productoServicio;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos() {
        return ResponseEntity.ok().body(productoServicio.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok().body(productoServicio.productoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardarProductos(@RequestBody ProductoRequestDTO productoRequestDto) {
        return ResponseEntity.ok().body(productoServicio.guardarProducto(productoRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@RequestBody ProductoRequestDTO productoRequestDto, @PathVariable Long id) {
        return ResponseEntity.ok().body(productoServicio.actualizarProducto(productoRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public void eliminarProductos(@PathVariable Long id) {
        productoServicio.eliminarProducto(id);
    }

}
