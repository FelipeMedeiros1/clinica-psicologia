package psi.voll.api.domain.psicologo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo,Long> {
    @Query("""
            select p from Psicologo p
            where
            p.ativo = true
            and
            p.especialidade = :especialidade
            and
            p.id not in(
                select c.psicologo.id from Consulta c
                where
                c.data = :data
            )
            order by random()
            limit 1
        """)
    Psicologo escolherPsicologoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select p.ativo
            from Psicologo p
            where
            p.id = :id
            """)
    boolean findAtivoById(Long id);

    boolean existsByEmailOrCrp(String email, String crp);

}
