package psi.voll.api.domain.consulta.validacoes.agendamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import psi.voll.api.domain.ValidacaoException;
import psi.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;
import psi.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidadorPsicologoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        //escolha do psicologo opcional
        if (dados.idPsicologo() == null) {
            return;
        }

        var psicologoEstaAtivo = repository.findAtivoById(dados.idPsicologo());
        if (!psicologoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com psicólogo excluído");
        }
    }

}
