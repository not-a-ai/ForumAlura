package com.br.alura.forumChallenge.domain.topico;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        Status status
) {}
