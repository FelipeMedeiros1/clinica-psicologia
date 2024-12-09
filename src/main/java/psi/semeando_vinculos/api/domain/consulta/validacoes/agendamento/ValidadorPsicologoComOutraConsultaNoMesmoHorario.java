package psi.semeando_vinculos.api.domain.consulta.validacoes.agendamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import psi.semeando_vinculos.api.domain.ValidacaoException;
import psi.semeando_vinculos.api.domain.consulta.ConsultaRepository;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosAgendamentoConsulta;

@Component
public class ValidadorPsicologoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var psicologoPossuiOutraConsultaNoMesmoHorario = repository.existsByPsicologoIdAndData(dados.idPsicologo(), dados.data());
        if (psicologoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Psicólogo já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
