package com.br.alura.forumChallenge.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastro(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email
        ) {

}
