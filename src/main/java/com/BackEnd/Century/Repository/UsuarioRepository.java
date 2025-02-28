package com.BackEnd.Century.Repository;

import com.BackEnd.Century.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
    Usuario findByCorreo(String correo);
}
