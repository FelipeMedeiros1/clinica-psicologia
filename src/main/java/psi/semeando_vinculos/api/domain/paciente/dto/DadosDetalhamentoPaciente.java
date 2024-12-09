package psi.semeando_vinculos.api.domain.paciente.dto;

import psi.semeando_vinculos.api.domain.endereco.Endereco;
import psi.semeando_vinculos.api.domain.paciente.Paciente;

public record DadosDetalhamentoPaciente(Long id, Boolean ativo, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getAtivo(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}