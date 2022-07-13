package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.UsuarioRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.UsuarioResponseDTO;
import com.belleza.tiendadecosmeticos.exceptions.DataBaseException;
import com.belleza.tiendadecosmeticos.modelo.Usuario;
import com.belleza.tiendadecosmeticos.repositorio.UsuarioRepositirio;
import com.belleza.tiendadecosmeticos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositirio usuarioRepositorio;

    @Override
    public UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        Usuario usuarioPorCrear = new Usuario();
        usuarioPorCrear.setCedula(usuarioRequestDTO.getCedula());
        usuarioPorCrear.setDireccion(usuarioRequestDTO.getDireccion());
        usuarioPorCrear.setNombre(usuarioRequestDTO.getNombre());

        Usuario nuevoUsuario = usuarioRepositorio.save(usuarioPorCrear);

        if (nuevoUsuario == null) {
            throw new DataBaseException("Hubo un error interno al guardar el usuario [" + usuarioRequestDTO.getNombre() + "], intente nuevamente");
        } else {
            return new UsuarioResponseDTO(nuevoUsuario.getId(),
                    nuevoUsuario.getNombre(),
                    nuevoUsuario.getCedula(),
                    nuevoUsuario.getDireccion());
        }

    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<UsuarioResponseDTO> usuarioResponseDTOList = new ArrayList<>();

        if (usuarios.isEmpty()) {
            return Collections.emptyList();
        } else {
            usuarios.parallelStream().forEach(usuario -> {
                usuarioResponseDTOList.add(new UsuarioResponseDTO(usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCedula(),
                        usuario.getDireccion()));
            });

            return usuarioResponseDTOList;
        }

    }

    @Override
    public UsuarioResponseDTO listarUnUsuarioPorId(Long id) {

        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (usuario == null) {
            throw new EntityNotFoundException("El usuario con la ID [" + id + "] no se encuentra en la base de datos");

        } else {
            return new UsuarioResponseDTO(usuario.getId(),
                    usuario.getNombre(),
                    usuario.getCedula(),
                    usuario.getDireccion());
        }

    }

    @Override
    public ResponseInfoDTO eliminarUsuario(Long id, HttpServletRequest httpServletRequest) {
        usuarioRepositorio.deleteById(id);
        if (usuarioRepositorio.findById(id).orElse(null) == null) {
            return new ResponseInfoDTO("Usuario con la Id [" + id + "] eliminado exitosamente",
                    httpServletRequest.getServletPath(),
                    HttpStatus.OK.value());
        } else {
            throw new DataBaseException("Hubo un error interno al borrar el usuario con ID [" + id + "], intente nuevamente");

        }

    }
}
