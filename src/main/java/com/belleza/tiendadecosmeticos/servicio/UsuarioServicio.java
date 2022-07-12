package com.belleza.tiendadecosmeticos.servicio;

import com.belleza.tiendadecosmeticos.dto.request.UsuarioRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.UsuarioResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Usuario;

public interface UsuarioServicio {

    UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO usuario);

}
