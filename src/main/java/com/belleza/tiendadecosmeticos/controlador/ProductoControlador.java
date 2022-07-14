package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.ProductoRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.servicio.Impl.ProductoServicioImpl;
import com.belleza.tiendadecosmeticos.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public ResponseEntity<ProductoResponseDTO> guardarProductos(@Valid @RequestBody ProductoRequestDTO productoRequestDto, BindingResult bindingResult) {
        Validation.validarParametros(bindingResult);
        return ResponseEntity.ok().body(productoServicio.guardarProducto(productoRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@Valid @RequestBody ProductoRequestDTO productoRequestDto, BindingResult bindingResult, @PathVariable Long id) {
        Validation.validarParametros(bindingResult); //TODO Testear este, no te olvides
        return ResponseEntity.ok().body(productoServicio.actualizarProducto(productoRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInfoDTO> eliminarProductos(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(productoServicio.eliminarProducto(id, httpServletRequest));
    }

}
