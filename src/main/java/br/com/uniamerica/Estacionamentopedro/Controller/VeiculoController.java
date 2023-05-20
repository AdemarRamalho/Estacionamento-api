package br.com.uniamerica.Estacionamentopedro.controller;

import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import br.com.uniamerica.Estacionamentopedro.repository.VeiculoRepository;
import br.com.uniamerica.Estacionamentopedro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Veiculo veiculo = this.veiculoService.findById(id);
        return veiculo == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(veiculo);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.veiculoService.listaCompleta());
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        return ResponseEntity.ok(this.veiculoService.listaVeiculosAtivos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Veiculo veiculo){
        try {
            return ResponseEntity.ok(veiculoService.cadastrar(veiculo));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{idVeiculo}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long idVeiculo,
            @RequestBody Veiculo veiculo
    ) {
        try {
            this.veiculoService.atualizar(idVeiculo, veiculo);
            return ResponseEntity.ok().body("Veiculo atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/desativar/{idVeiculo}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idVeiculo
    ){
        try{
            this.veiculoService.desativar(idVeiculo);
            return ResponseEntity.ok().body("Veiculo desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ativar/{idVeiculo}")
    public ResponseEntity<?> ativar(
            @PathVariable Long idVeiculo
    ){
        try{
            this.veiculoService.ativar(idVeiculo);
            return ResponseEntity.ok().body("Veiculo ativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

