package psi.voll.api.domain.psicologo;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import psi.voll.api.domain.endereco.Endereco;
import psi.voll.api.domain.psicologo.dto.DadosAtualizacaoPsicologo;
import psi.voll.api.domain.psicologo.dto.DadosCadastroPsicologo;

@Table(name = "psicologos")
@Entity(name = "Psicologo")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crp;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Psicologo(DadosCadastroPsicologo dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crp = dados.crp();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }
    public void atualizarInformacoes(DadosAtualizacaoPsicologo dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}