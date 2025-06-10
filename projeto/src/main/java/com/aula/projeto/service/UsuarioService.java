package com.aula.projeto.service;

import com.aula.projeto.dto.UsuarioDTO;
import com.aula.projeto.model.UsuarioEntity;
import com.aula.projeto.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioEntity salvarUsuario(UsuarioDTO dto) {
        if(usuarioRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("E-mail já cadastrado: ");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioEntity> ListarTodosUsuarios(){
        return usuarioRepository.findAll();
    }
}
