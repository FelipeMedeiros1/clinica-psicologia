package psi.voll.api.domain.psicologo.dto;

import psi.voll.api.domain.endereco.Endereco;
import psi.voll.api.domain.psicologo.Especialidade;
import psi.voll.api.domain.psicologo.Psicologo;

public record DadosDetalhamentoPsicologo(
        Long id, Boolean ativo, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoPsicologo(Psicologo psicologo) {
        this(psicologo.getId(), psicologo.getAtivo(), psicologo.getNome(), psicologo.getEmail(), psicologo.getCrp(), psicologo.getTelefone(), psicologo.getEspecialidade(), psicologo.getEndereco());
    }
}