package com.belleza.tiendadecosmeticos.controlador;

import com.belleza.tiendadecosmeticos.dto.ResponseInfoDTO;
import com.belleza.tiendadecosmeticos.dto.request.UsuarioRequestDTO;
import com.belleza.tiendadecosmeticos.dto.response.UsuarioResponseDTO;
import com.belleza.tiendadecosmeticos.servicio.Impl.UsuarioServicioImpl;
import com.belleza.tiendadecosmeticos.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> guardarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO, BindingResult bindingResult) {
        Validation.validarParametros(bindingResult);
        return ResponseEntity.ok().body(usuarioServicio.guardarUsuario(usuarioRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioServicio.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> listarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioServicio.listarUnUsuarioPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInfoDTO> eliminarUsuario(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(usuarioServicio.eliminarUsuario(id, httpServletRequest));
    }

}
