package psi.semeando_vinculos.api.domain.consulta.validacoes.agendamento;


import org.springframework.stereotype.Component;
import psi.semeando_vinculos.api.domain.ValidacaoException;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosAgendamentoConsulta;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();
        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7 ;
        boolean depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18 || (dataConsulta.getHour() == 18 && dataConsulta.getMinute() > 0);
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
