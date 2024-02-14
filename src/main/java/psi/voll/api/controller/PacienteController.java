package psi.voll.api.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import psi.voll.api.domain.paciente.CadastroPacienteService;
import psi.voll.api.domain.paciente.PacienteRepository;
import psi.voll.api.domain.paciente.dto.DadosAtualizacaoPaciente;
import psi.voll.api.domain.paciente.dto.DadosCadastroPaciente;
import psi.voll.api.domain.paciente.dto.DadosDetalhamentoPaciente;
import psi.voll.api.domain.paciente.dto.DadosListagemPaciente;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private CadastroPacienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var detalhesPaciente = service.cadastrar(dados);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(detalhesPaciente.id()).toUri();
        return ResponseEntity.created(uri).body(detalhesPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(repository.findAll(paginacao).map(DadosListagemPaciente::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(repository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.getReferenceById(id).desativar();

        return ResponseEntity.noContent().build();
    }

}
