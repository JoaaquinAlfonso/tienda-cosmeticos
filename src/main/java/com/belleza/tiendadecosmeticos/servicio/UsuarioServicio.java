package com.belleza.tiendadecosmeticos.servicio;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.UsuarioRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.UsuarioResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UsuarioServicio {

    UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO usuario);

    List<UsuarioResponseDTO> listarUsuarios();

    UsuarioResponseDTO listarUnUsuarioPorId(Long id);

    ResponseInfoDTO eliminarUsuario(Long id, HttpServletRequest httpServletRequest);

}
