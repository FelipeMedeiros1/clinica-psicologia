package psi.semeando_vinculos.api.domain.psicologo.dto;

import jakarta.validation.constraints.NotNull;
import psi.semeando_vinculos.api.domain.endereco.dto.DadosEndereco;

public record DadosAtualizacaoPsicologo(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
