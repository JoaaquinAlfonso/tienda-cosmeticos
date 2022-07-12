package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.modelo.Usuario;
import com.belleza.tiendadecosmeticos.servicio.Impl.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicioImpl vendedorServicio;

    @PostMapping
    public void guardarVendedor(@RequestBody Usuario usuario){
        vendedorServicio.guardarUsuario(usuario);
    }

}
