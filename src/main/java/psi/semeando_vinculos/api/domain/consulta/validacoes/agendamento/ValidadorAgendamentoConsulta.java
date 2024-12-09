package psi.semeando_vinculos.api.domain.consulta.validacoes.agendamento;


import psi.semeando_vinculos.api.domain.consulta.dto.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
