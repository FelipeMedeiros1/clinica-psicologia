package psi.semeando_vinculos.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psi.semeando_vinculos.api.domain.consulta.AgendaConsultaService;
import psi.semeando_vinculos.api.domain.consulta.ConsultaRepository;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosAgendamentoConsulta;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosListagemConsulta;
import psi.semeando_vinculos.api.domain.consulta.dto.DadosRelatorioConsultaMensal;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultaService agendaConsultas;
    @Autowired
    private ConsultaRepository consultaRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        DadosDetalhamentoConsulta detalhamentoConsulta = agendaConsultas.agendar(dados);
        return ResponseEntity.ok(detalhamentoConsulta);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listarProximasConsultas(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        var proximasConsultas = consultaRepository.findAllByDataGreaterThan(LocalDateTime.now(), paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(proximasConsultas);
    }

    @GetMapping("/relatorio-mensal/{mes}")
    public ResponseEntity<List<DadosRelatorioConsultaMensal>> gerarRelatorioConsultaMensal(@PathVariable YearMonth anoMes) {
        LocalDateTime inicioMes = anoMes.atDay(1).atStartOfDay();
        LocalDateTime fimMes = anoMes.atEndOfMonth().atTime(23, 59, 59);

        List<DadosRelatorioConsultaMensal> relatorio = consultaRepository.gerarRelatorioConsultaMensal(inicioMes, fimMes);
        return ResponseEntity.ok(relatorio);
    }
}
