package com.BackEnd.Century.Service;

import com.BackEnd.Century.Model.Usuario;
import com.BackEnd.Century.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioRequest) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Cliente con ese ID no existe : " + id));

        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setCorreo(usuarioRequest.getCorreo());
        usuario.setPassword(usuarioRequest.getPassword());

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Cliente con ese ID no existe : " + id));

        usuarioRepository.delete(usuario);
    }

}
