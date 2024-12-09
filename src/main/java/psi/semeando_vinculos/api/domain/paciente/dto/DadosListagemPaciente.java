package psi.semeando_vinculos.api.domain.paciente.dto;

import psi.semeando_vinculos.api.domain.paciente.Paciente;

public record DadosListagemPaciente(Long id, Boolean ativo, String nome, String email, String cpf) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getAtivo(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
