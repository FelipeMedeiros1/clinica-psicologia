package psi.semeando_vinculos.api.domain.consulta.dto;

import psi.semeando_vinculos.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idPsicologo, Long idPaciente, LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getPsicologo().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
