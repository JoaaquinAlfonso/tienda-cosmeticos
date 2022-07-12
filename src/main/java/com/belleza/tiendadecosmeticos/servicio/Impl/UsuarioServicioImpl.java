package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.UsuarioRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.UsuarioResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Usuario;
import com.belleza.tiendadecosmeticos.repositorio.UsuarioRepositirio;
import com.belleza.tiendadecosmeticos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositirio usuarioRepositorio;

    @Override
    public UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            Usuario usuarioPorCrear = new Usuario();
            usuarioPorCrear.setCedula(usuarioRequestDTO.getCedula());
            usuarioPorCrear.setDireccion(usuarioRequestDTO.getDireccion());
            usuarioPorCrear.setNombre(usuarioRequestDTO.getNombre());

            Usuario nuevoUsuario = usuarioRepositorio.save(usuarioPorCrear);

            if (nuevoUsuario == null) {
                //TODO Agregar lanzamiento de excepción personalizada.
                //return ResponseEntity.notFound().build();
            } else {
                return new UsuarioResponseDTO(nuevoUsuario.getId(),
                        nuevoUsuario.getNombre(),
                        nuevoUsuario.getCedula(),
                        nuevoUsuario.getDireccion());
            }
        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada.
            //System.out.println(e);
        }

        return null;
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<UsuarioResponseDTO> usuarioResponseDTOList = new ArrayList<>();

        if (usuarios.isEmpty()) {
            //TODO Hacer que retorne una excepción de lista vacía
        } else {
            usuarios.parallelStream().forEach(usuario -> {
                usuarioResponseDTOList.add(new UsuarioResponseDTO(usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCedula(),
                        usuario.getDireccion()));
            });

            return usuarioResponseDTOList;
        }

        return null;
    }

    @Override
    public UsuarioResponseDTO listarUnUsuarioPorId(Long id) {
        try {
            Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

            if (usuario == null) {
                //TODO Agregar lanzamiento de excepción personalizada o una mejor.

            } else {
                return new UsuarioResponseDTO(usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCedula(),
                        usuario.getDireccion());
            }

        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada o una mejor.
        }

        return null;
    }

    @Override
    public ResponseInfoDTO eliminarUsuario(Long id, HttpServletRequest httpServletRequest) {
        try {
            usuarioRepositorio.deleteById(id);

            return new ResponseInfoDTO("Usuario con la Id [" + id + "] eliminado exitosamente",
                    httpServletRequest.getServletPath(),
                    HttpStatus.OK.value());

        } catch (Exception e) {
            //TODO Agregar lanzamiento de excepción personalizada o una mejor.
            //System.out.println(e);
        }

        return null;
    }
}
