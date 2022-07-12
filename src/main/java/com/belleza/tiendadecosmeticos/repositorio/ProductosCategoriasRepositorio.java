package com.belleza.tiendadecosmeticos.repositorio;

import com.belleza.tiendadecosmeticos.modelo.ProductosCategorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosCategoriasRepositorio extends JpaRepository<ProductosCategorias, Long> {
}
