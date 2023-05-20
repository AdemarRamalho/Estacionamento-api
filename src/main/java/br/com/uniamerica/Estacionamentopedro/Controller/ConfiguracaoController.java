package br.com.uniamerica.Estacionamentopedro.controller;

import br.com.uniamerica.Estacionamentopedro.entity.Configuracao;
import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import br.com.uniamerica.Estacionamentopedro.service.ConfiguracaoService;
import br.com.uniamerica.Estacionamentopedro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Configuracao configuracao = this.configuracaoService.findById(id);
        return configuracao == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(configuracao);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Configuracao configuracao){
        try {
            return ResponseEntity.ok(configuracaoService.cadastrar(configuracao));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{idConfiguracao}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long idConfiguracao,
            @RequestBody Configuracao configuracao
    ) {
        try {
            this.configuracaoService.atualizar(idConfiguracao, configuracao);
            return ResponseEntity.ok().body("Configuracao atualizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
