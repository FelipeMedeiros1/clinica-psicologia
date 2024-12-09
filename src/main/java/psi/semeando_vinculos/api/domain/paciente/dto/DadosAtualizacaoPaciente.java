package psi.semeando_vinculos.api.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import psi.semeando_vinculos.api.domain.endereco.dto.DadosEndereco;

public record DadosAtualizacaoPaciente(


        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco) {
}
