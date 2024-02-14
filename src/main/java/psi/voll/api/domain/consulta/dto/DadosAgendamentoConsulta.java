package psi.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import psi.voll.api.domain.psicologo.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idPsicologo,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}
