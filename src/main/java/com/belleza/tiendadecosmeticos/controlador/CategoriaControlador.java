package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.modelo.Categoria;
import com.belleza.tiendadecosmeticos.modelo.Producto;
import com.belleza.tiendadecosmeticos.servicio.Impl.CategoriaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/api/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServicioImpl categoriaServicio;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return categoriaServicio.listarCategorias();
    }

    @PostMapping
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
        return categoriaServicio.guardarCategorias(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Long id){
        categoriaServicio.eliminarCategoria(id);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<Collection<Producto>> listarProductoPorCategoria(@PathVariable("id") Long id) {
        return categoriaServicio.listarProductoPorCategoria(id);
    }

}
