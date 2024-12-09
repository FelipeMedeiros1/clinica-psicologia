package psi.semeando_vinculos.api.domain.psicologo.dto;

import psi.semeando_vinculos.api.domain.endereco.Endereco;
import psi.semeando_vinculos.api.domain.psicologo.Especialidade;
import psi.semeando_vinculos.api.domain.psicologo.Psicologo;

public record DadosDetalhamentoPsicologo(
        Long id, Boolean ativo, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoPsicologo(Psicologo psicologo) {
        this(psicologo.getId(), psicologo.getAtivo(), psicologo.getNome(), psicologo.getEmail(), psicologo.getCrp(), psicologo.getTelefone(), psicologo.getEspecialidade(), psicologo.getEndereco());
    }
}