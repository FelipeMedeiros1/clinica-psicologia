package psi.semeando_vinculos.api.domain.psicologo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psi.semeando_vinculos.api.domain.ValidacaoException;
import psi.semeando_vinculos.api.domain.psicologo.dto.DadosCadastroPsicologo;
import psi.semeando_vinculos.api.domain.psicologo.dto.DadosDetalhamentoPsicologo;

@Service
public class CadastroPsicologoService {
    @Autowired
    private PsicologoRepository repository;

    public DadosDetalhamentoPsicologo cadastrar(DadosCadastroPsicologo dados){
        var jaCadastrado = repository.existsByEmailOrCrp(dados.email(), dados.crp());
        if (jaCadastrado){
            throw new ValidacaoException("Já existe outro psicologo com o email ou crp informado!");
        }

        var psi = new Psicologo(dados);
        repository.save(psi);
        return new DadosDetalhamentoPsicologo(psi);

    }








}
