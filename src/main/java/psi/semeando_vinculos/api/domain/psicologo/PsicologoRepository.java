package psi.semeando_vinculos.api.domain.psicologo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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

    @Query("select p from Psicologo p where lower(p.nome) like %:nome%")
    List<Psicologo> findByNomeContainingIgnoreCase(@Param("nome") String nome);


    boolean existsByEmailOrCrp(String email, String crp);

}
