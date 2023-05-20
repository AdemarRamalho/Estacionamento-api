package br.com.uniamerica.Estacionamentopedro.controller;

import br.com.uniamerica.Estacionamentopedro.entity.Movimentacao;
import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import br.com.uniamerica.Estacionamentopedro.service.MovimentacaoService;
import br.com.uniamerica.Estacionamentopedro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping(value = "api/movimentacoes")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Movimentacao movimentacao = this.movimentacaoService.findById(id);
        return movimentacao == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.movimentacaoService.listaCompleta());
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        return ResponseEntity.ok(this.movimentacaoService.listaMovimentacoesAtivas());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Movimentacao movimentacao){
        try {
            return ResponseEntity.ok(movimentacaoService.cadastrar(movimentacao));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{idMovimentacao}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long idMovimentacao,
            @RequestBody Movimentacao movimentacao
    ) {
        try {
            this.movimentacaoService.atualizar(idMovimentacao, movimentacao);
            return ResponseEntity.ok().body("Movimentacao atualizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/desativar/{idMovimentacao}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idMovimentacao
    ){
        try{
            this.movimentacaoService.desativar(idMovimentacao);
            return ResponseEntity.ok().body("Veiculo desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ativar/{idMovimentacao}")
    public ResponseEntity<?> ativar(
            @PathVariable Long idMovimentacao
    ){
        try{
            this.movimentacaoService.ativar(idMovimentacao);
            return ResponseEntity.ok().body("Movimentacao ativada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
