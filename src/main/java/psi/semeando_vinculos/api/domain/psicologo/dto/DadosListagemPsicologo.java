package psi.semeando_vinculos.api.domain.psicologo.dto;

import psi.semeando_vinculos.api.domain.psicologo.Especialidade;
import psi.semeando_vinculos.api.domain.psicologo.Psicologo;

public record DadosListagemPsicologo(Long id, Boolean ativo, String nome, String email, String crp, Especialidade especialidade) {

    public DadosListagemPsicologo(Psicologo psicologo) {
        this(psicologo.getId(), psicologo.getAtivo(), psicologo.getNome(), psicologo.getEmail(), psicologo.getCrp(), psicologo.getEspecialidade());
    }

}
