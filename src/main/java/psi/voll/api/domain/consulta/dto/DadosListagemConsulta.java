package psi.voll.api.domain.consulta.dto;


import psi.voll.api.domain.consulta.Consulta;
import psi.voll.api.domain.psicologo.Especialidade;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id, LocalDateTime data, Especialidade especialidade, String medico, String paciente) {

    public DadosListagemConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getData(), consulta.getPsicologo().getEspecialidade(), consulta.getPsicologo().getNome(), consulta.getPaciente().getNome());
    }

}
