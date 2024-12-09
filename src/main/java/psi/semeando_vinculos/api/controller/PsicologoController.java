package psi.semeando_vinculos.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import psi.semeando_vinculos.api.domain.psicologo.CadastroPsicologoService;
import psi.semeando_vinculos.api.domain.psicologo.PsicologoRepository;
import psi.semeando_vinculos.api.domain.psicologo.dto.DadosAtualizacaoPsicologo;
import psi.semeando_vinculos.api.domain.psicologo.dto.DadosCadastroPsicologo;
import psi.semeando_vinculos.api.domain.psicologo.dto.DadosDetalhamentoPsicologo;
import psi.semeando_vinculos.api.domain.psicologo.dto.DadosListagemPsicologo;

import java.net.URI;

@RestController
@RequestMapping("psicologos")
public class PsicologoController {
    @Autowired
    private PsicologoRepository psicologoRepository;

    @Autowired
    private CadastroPsicologoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPsicologo> cadastrar(
            @RequestBody @Valid DadosCadastroPsicologo dados, UriComponentsBuilder uriBuilder) {
        var detalhamentoPsicologo = service.cadastrar(dados);
        URI uri = uriBuilder.path("/psicologos/{id}").buildAndExpand(detalhamentoPsicologo.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamentoPsicologo);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPsicologo>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var psicologos = psicologoRepository.findAll(paginacao).map(DadosListagemPsicologo::new);
        return ResponseEntity.ok(psicologos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPsicologo> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(new DadosDetalhamentoPsicologo(psicologoRepository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPsicologo> atualizar(@RequestBody @Valid DadosAtualizacaoPsicologo dados) {
        var psicologo = psicologoRepository.getReferenceById(dados.id());
        psicologo.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPsicologo(psicologo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        psicologoRepository.getReferenceById(id).desativar();

        return ResponseEntity.noContent().build();
    }


}
