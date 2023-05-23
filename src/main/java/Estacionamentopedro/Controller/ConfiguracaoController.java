package Estacionamentopedro.Controller;

import Estacionamentopedro.Entity.Configuracao;
import Estacionamentopedro.Service.ConfiguracaoService;
import Estacionamentopedro.Entity.Configuracao;
import Estacionamentopedro.Entity.Veiculo;
import Estacionamentopedro.Service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        try{
            this.configuracaoService.findById(id);
            return ResponseEntity.ok().body(this.configuracaoService.findById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Id não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Configuracao configuracao){
        try {
            this.configuracaoService.cadastrar(configuracao);
            return ResponseEntity.ok().body("Configuracao Cadastrada com Sucesso!");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro ao arrumar configuracao!");
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id,
                                    @Validated @RequestBody final Configuracao configuracao){
        Configuracao configuracaoBanco = configuracaoService.findById(id);
        configuracaoBanco.setVagasMoto(configuracao.getVagasMoto());
        configuracaoBanco.setVagasCarro(configuracao.getVagasCarro());
        configuracaoBanco.setVagasVan(configuracao.getVagasVan());
        configuracaoBanco.setValorHora(configuracao.getValorHora());
        configuracaoBanco.setValorMinutoMulta(configuracao.getValorMinutoMulta());
        try {
            this.configuracaoService.atualizar(configuracaoBanco);
            return ResponseEntity.ok("Configuracao alterada com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro ao alterar configuracao!");
        }
    }

}