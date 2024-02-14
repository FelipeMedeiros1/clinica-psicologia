package psi.voll.api.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import psi.voll.api.domain.endereco.dto.DadosEndereco;

public record DadosAtualizacaoPaciente(


        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco) {
}
