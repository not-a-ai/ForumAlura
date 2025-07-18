package com.br.alura.forumChallenge.controller;

import com.br.alura.forumChallenge.domain.perfil.Perfil;
import com.br.alura.forumChallenge.domain.perfil.PerfilRepository;
import com.br.alura.forumChallenge.domain.usuario.DadosCadastro;
import com.br.alura.forumChallenge.domain.usuario.Usuario;
import com.br.alura.forumChallenge.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;


    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Perfil perfilExistente = perfilRepository.findByNome(usuario.getPerfil().getNome());

        if (perfilExistente == null) {
            return ResponseEntity.badRequest().build();
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setPerfil(perfilExistente);

        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(salvo);
    }


    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
