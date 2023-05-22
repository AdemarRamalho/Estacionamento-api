package Estacionamentopedro.Controller;

import Estacionamentopedro.Entity.Movimentacao;
import Estacionamentopedro.Service.MovimentacaoService;
import Estacionamentopedro.Entity.Movimentacao;
import Estacionamentopedro.Entity.Veiculo;
import Estacionamentopedro.Service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/movimentacoes")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        try{
            this.movimentacaoService.findById(id);
            return ResponseEntity.ok().body(this.movimentacaoService.findById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Id não encontrado!");
        }
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        try{
            return ResponseEntity.ok(this.movimentacaoService.listaCompleta());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Lista de movimentacoes indisponivel!");
        }
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos() {
        try {
            return ResponseEntity.ok(this.movimentacaoService.listaMovimentacoesAtivas());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Nenhuma movimentacao ativa no momento!");
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Movimentacao movimentacao){
        try {
            this.movimentacaoService.cadastrar(movimentacao);
            return ResponseEntity.ok().body("movimentacao Cadastrado com Sucesso!");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id,
                                    @Validated @RequestBody final Movimentacao movimentacao){
        Movimentacao movimentacaoBanco = movimentacaoService.findById(id);
        movimentacaoBanco.setTempoDesconto(movimentacaoBanco.getTempoDesconto());
        movimentacaoBanco.setTempoHoras(movimentacao.getTempoHoras());
        movimentacaoBanco.setVeiculo(movimentacao.getVeiculo());
        movimentacaoBanco.setCondutor(movimentacao.getCondutor());
        movimentacaoBanco.setValorHora(movimentacao.getValorHora());

        try {
            this.movimentacaoService.atualizar(movimentacaoBanco);
            return ResponseEntity.ok("Movimentacao alterada com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro ao alterar movimentacao!");
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
            return ResponseEntity.badRequest().body("Id ja desativado ou nao encontrado!");
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
            return ResponseEntity.badRequest().body("Id ja ativado ou nao encontrado!");
        }
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizar(@PathVariable("id")final Long id, @RequestBody final Movimentacao movimentacao){
        Movimentacao movimentacao1 = this.movimentacaoService.findById(id);
        movimentacao1.setSaida(movimentacao.getSaida());
        try {
            this.movimentacaoService.finalizaMov(id, movimentacao1);
            return ResponseEntity.ok(movimentacao1.toString());
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Erro ao finalizar movimentação!");
        }
    }
}