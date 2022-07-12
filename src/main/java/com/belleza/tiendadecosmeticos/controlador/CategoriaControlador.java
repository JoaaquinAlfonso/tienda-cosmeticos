package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.CategoriaRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.CategoriaResponseDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.servicio.Impl.CategoriaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServicioImpl categoriaServicio;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        return ResponseEntity.ok().body(categoriaServicio.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> guardarCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        return ResponseEntity.ok().body(categoriaServicio.guardarCategorias(categoriaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInfoDTO> eliminarCategoria(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(categoriaServicio.eliminarCategoria(id, httpServletRequest));
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductoPorCategoria(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(categoriaServicio.listarProductoPorCategoria(id));
    }

}
