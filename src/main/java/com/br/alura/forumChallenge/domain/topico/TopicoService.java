package com.br.alura.forumChallenge.domain.topico;

import com.br.alura.forumChallenge.domain.DadosTopico;
import com.br.alura.forumChallenge.domain.ValidacaoException;
import com.br.alura.forumChallenge.domain.curso.Curso;
import com.br.alura.forumChallenge.domain.curso.CursoRepository;
import com.br.alura.forumChallenge.domain.usuario.Usuario;
import com.br.alura.forumChallenge.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico criarTopico(DadosTopico request, String username) {
        Usuario autor = (Usuario) usuarioRepository.findByEmail(username);
        if (autor == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        Topico topico = new Topico(
                request.titulo(),
                request.mensagem(),
                autor,
                curso
        );

        Topico salvo = topicoRepository.save(topico);

        return salvo;
    }
}

