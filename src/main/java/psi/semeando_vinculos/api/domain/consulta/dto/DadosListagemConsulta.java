package psi.semeando_vinculos.api.domain.consulta.dto;


import psi.semeando_vinculos.api.domain.psicologo.Especialidade;
import psi.semeando_vinculos.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id, LocalDateTime data, Especialidade especialidade, String medico, String paciente) {

    public DadosListagemConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getData(), consulta.getPsicologo().getEspecialidade(), consulta.getPsicologo().getNome(), consulta.getPaciente().getNome());
    }

}
