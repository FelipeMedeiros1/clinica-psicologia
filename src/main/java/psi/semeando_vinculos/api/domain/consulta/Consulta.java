package psi.semeando_vinculos.api.domain.consulta;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import psi.semeando_vinculos.api.domain.paciente.Paciente;
import psi.semeando_vinculos.api.domain.psicologo.Psicologo;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psicologo_id")
    private Psicologo psicologo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    public Consulta(Psicologo psicologo, Paciente paciente, LocalDateTime data) {
        this.psicologo = psicologo;
        this.paciente = paciente;
        this.data = data;
    }
}
