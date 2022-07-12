package com.belleza.tiendadecosmeticos.servicio.Impl;

import com.belleza.tiendadecosmeticos.modelo.Usuario;
import com.belleza.tiendadecosmeticos.repositorio.UsuarioRepositirio;
import com.belleza.tiendadecosmeticos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositirio usuarioRepositirio;

    @Override
    public ResponseEntity<Usuario> guardarUsuario(Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioRepositirio.save(usuario);
            if (nuevoUsuario == null){
                return ResponseEntity.notFound().build();
            }else{
                return ResponseEntity.ok(usuario);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }
}
