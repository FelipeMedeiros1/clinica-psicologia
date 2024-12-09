package psi.semeando_vinculos.api.domain.consulta.validacoes.agendamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import psi.semeando_vinculos.api.domain.ValidacaoException;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosAgendamentoConsulta;
import psi.semeando_vinculos.api.domain.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }

}
