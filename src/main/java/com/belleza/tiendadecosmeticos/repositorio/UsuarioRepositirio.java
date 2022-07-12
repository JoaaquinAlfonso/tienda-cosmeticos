package com.belleza.tiendadecosmeticos.repositorio;

import com.belleza.tiendadecosmeticos.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositirio extends JpaRepository<Usuario, Long> {
}
