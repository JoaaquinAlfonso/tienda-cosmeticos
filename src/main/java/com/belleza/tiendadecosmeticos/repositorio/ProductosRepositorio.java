package com.belleza.tiendadecosmeticos.repositorio;

import com.belleza.tiendadecosmeticos.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepositorio extends JpaRepository<Producto, Long> {
}
