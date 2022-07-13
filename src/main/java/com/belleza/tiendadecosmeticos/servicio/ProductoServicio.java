package com.belleza.tiendadecosmeticos.servicio;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.ProductoRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductoServicio {
    List<ProductoResponseDTO> listarProductos();

    ProductoResponseDTO guardarProducto(ProductoRequestDTO productoRequestDto);

    ResponseInfoDTO eliminarProducto(Long id, HttpServletRequest httpServletRequest);

    ProductoResponseDTO productoPorId(Long id);

    ProductoResponseDTO actualizarProducto(ProductoRequestDTO productoRequestDto, Long id);

}
