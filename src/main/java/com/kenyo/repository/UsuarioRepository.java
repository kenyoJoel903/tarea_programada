package com.kenyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenyo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
