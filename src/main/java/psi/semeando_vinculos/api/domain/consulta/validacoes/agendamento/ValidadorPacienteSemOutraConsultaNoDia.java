package psi.semeando_vinculos.api.domain.consulta.validacoes.agendamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import psi.semeando_vinculos.api.domain.ValidacaoException;
import psi.semeando_vinculos.api.domain.consulta.ConsultaRepository;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosAgendamentoConsulta;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
