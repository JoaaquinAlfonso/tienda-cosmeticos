package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.dto.response.CategoriaResponseDTO;
import com.belleza.tiendadecosmeticos.dto.response.ProductoResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Categoria;
import com.belleza.tiendadecosmeticos.servicio.Impl.CategoriaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CategoriaResponseDTO> guardarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok().body(categoriaServicio.guardarCategorias(categoria));
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Long id) {
        categoriaServicio.eliminarCategoria(id);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductoPorCategoria(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(categoriaServicio.listarProductoPorCategoria(id));
    }

}
