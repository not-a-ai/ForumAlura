package com.br.alura.forumChallenge.domain;

import com.br.alura.forumChallenge.domain.topico.Status;

import java.time.LocalDateTime;

public record DadosTopico(
        String titulo,
        String mensagem,
        Long cursoId
) {}

