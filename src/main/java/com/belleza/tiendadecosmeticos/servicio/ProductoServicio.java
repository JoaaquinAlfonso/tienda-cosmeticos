package com.belleza.tiendadecosmeticos.servicio;

import com.belleza.tiendadecosmeticos.dto.ProductoRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoServicio {
    List<ProductoResponseDTO> listarProductos();

    ProductoResponseDTO guardarProducto(ProductoRequestDTO productoRequestDto);

    ResponseEntity<Producto> eliminarProducto(Long id);

    ProductoResponseDTO productoPorId(Long id);

    ProductoResponseDTO actualizarProducto(ProductoRequestDTO productoRequestDto, Long id);

}
