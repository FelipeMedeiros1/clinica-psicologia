package psi.voll.api.domain.psicologo.dto;

import jakarta.validation.constraints.NotNull;
import psi.voll.api.domain.endereco.dto.DadosEndereco;

public record DadosAtualizacaoPsicologo(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
