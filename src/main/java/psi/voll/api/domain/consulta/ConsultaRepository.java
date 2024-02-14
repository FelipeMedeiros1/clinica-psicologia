package psi.voll.api.domain.consulta;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import psi.voll.api.domain.consulta.dto.DadosRelatorioConsultaMensal;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByPsicologoIdAndData(Long idPsicologo, LocalDateTime data);

    Page<Consulta> findAllByDataGreaterThan(LocalDateTime data, Pageable paginacao);
    @Query("""
        SELECT new psi.voll.api.domain.consulta.dto.DadosRelatorioConsultaMensal(p.nome, p.crp, COUNT(c))
        FROM Consulta c JOIN c.psicologo p
        WHERE c.data >= :inicioMes AND c.data <= :fimMes
        GROUP BY p.nome, p.crp
    """)
    List<DadosRelatorioConsultaMensal> gerarRelatorioConsultaMensal(LocalDateTime inicioMes, LocalDateTime fimMes);
}
