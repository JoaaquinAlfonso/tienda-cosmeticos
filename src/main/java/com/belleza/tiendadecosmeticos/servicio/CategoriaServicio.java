package com.belleza.tiendadecosmeticos.servicio;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.CategoriaRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.CategoriaResponseDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Categoria;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CategoriaServicio {

    /*
     * Creacion de metodos a utilizar, en esta interface no va la logica.
     */
    List<CategoriaResponseDTO> listarCategorias();

    CategoriaResponseDTO guardarCategorias(CategoriaRequestDTO categoriaRequestDTO);

    ResponseInfoDTO eliminarCategoria(Long id, HttpServletRequest httpServletRequest);

    List<ProductoResponseDTO> listarProductoPorCategoria(Long id);

}
