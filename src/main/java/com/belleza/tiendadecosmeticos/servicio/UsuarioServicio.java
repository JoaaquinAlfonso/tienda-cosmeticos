package com.belleza.tiendadecosmeticos.servicio;

import com.belleza.tiendadecosmeticos.modelo.Usuario;
import org.springframework.http.ResponseEntity;

public interface UsuarioServicio {

    ResponseEntity<Usuario>  guardarUsuario(Usuario usuario);

}
