package com.br.alura.forumChallenge.controller;

import com.br.alura.forumChallenge.domain.DadosTopico;
import com.br.alura.forumChallenge.domain.topico.DadosAtualizacaoTopico;
import com.br.alura.forumChallenge.domain.topico.Topico;
import com.br.alura.forumChallenge.domain.topico.TopicoRepository;
import com.br.alura.forumChallenge.domain.topico.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicoRepository topicoRepository;


    @GetMapping
    public ResponseEntity<List<Topico>> listar() {
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> listarPorId(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = optionalTopico.get();

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setStatus(dados.status());

        return ResponseEntity.ok(topico);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<Topico> criar(
            @RequestBody @Valid DadosTopico request,
            @AuthenticationPrincipal UserDetails usuarioLogado
    ) {
        Topico topico = topicoService.criarTopico(request, usuarioLogado.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
