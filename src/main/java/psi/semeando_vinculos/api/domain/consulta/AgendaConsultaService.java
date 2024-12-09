package psi.semeando_vinculos.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psi.semeando_vinculos.api.domain.ValidacaoException;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosAgendamentoConsulta;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import psi.semeando_vinculos.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import psi.semeando_vinculos.api.domain.paciente.PacienteRepository;
import psi.semeando_vinculos.api.domain.psicologo.Psicologo;
import psi.semeando_vinculos.api.domain.psicologo.PsicologoRepository;

import java.util.List;

@Service
public class AgendaConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadoresAgendamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idPsicologo() != null && !psicologoRepository.existsById(dados.idPsicologo())) {
            throw new ValidacaoException("Id do psicólogo informado não existe!");
        }

        validadoresAgendamento.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var psicologo = escolherPsicologo(dados);
        if (psicologo == null) {
            throw new ValidacaoException("Não existe psicólogo disponível nessa data!");
        }

        var consulta = new Consulta(psicologo, paciente, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Psicologo escolherPsicologo(DadosAgendamentoConsulta dados) {
        if (dados.idPsicologo() != null) {
            return psicologoRepository.getReferenceById(dados.idPsicologo());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando psicólogo não for escolhido!");
        }

        return psicologoRepository.escolherPsicologoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

}
