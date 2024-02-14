package psi.voll.api.domain.consulta.validacoes.agendamento;


import psi.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
