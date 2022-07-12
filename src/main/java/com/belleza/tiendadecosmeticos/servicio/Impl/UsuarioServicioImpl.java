package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.dto.request.UsuarioRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.UsuarioResponseDTO;
import com.belleza.tiendadecosmeticos.modelo.Usuario;
import com.belleza.tiendadecosmeticos.repositorio.UsuarioRepositirio;
import com.belleza.tiendadecosmeticos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
