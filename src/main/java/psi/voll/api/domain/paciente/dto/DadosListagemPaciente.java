package psi.voll.api.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import psi.voll.api.domain.endereco.dto.DadosEndereco;
import psi.voll.api.domain.paciente.Paciente;

public record DadosListagemPaciente(Long id, Boolean ativo, String nome, String email, String cpf) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getAtivo(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
