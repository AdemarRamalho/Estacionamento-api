package Estacionamentopedro.Controller;


import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Entity.Modelo;
import Estacionamentopedro.Service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/modelos")
public class ModeloController {
    @Autowired
    private ModeloService modeloService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        try{
            this.modeloService.findById(id);
            return ResponseEntity.ok().body(this.modeloService.findById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Id não encontrado!");
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        try{
            return ResponseEntity.ok(this.modeloService.listaCompleta());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Lista indisponivel no momento!");
        }
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        try{
            return ResponseEntity.ok(this.modeloService.listaModelosAtivos());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Nenhum modelo ativo no momento!");
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Modelo modelo){
        try {
            return ResponseEntity.ok(modeloService.cadastrar(modelo));
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro Modelo ja cadastrado!");
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id,
                                    @Validated @RequestBody final Modelo modelo){
        Modelo modeloBanco = modeloService.findById(id);
        modeloBanco.setNome(modelo.getNome());

        try {
            this.modeloService.atualizar(modeloBanco);
            return ResponseEntity.ok("Modelo alterado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro ao alterar modelo!");
        }
    }

    @PutMapping("/desativar/{idModelo}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idModelo
    ){
        try{
            this.modeloService.desativar(idModelo);
            return ResponseEntity.ok().body("Modelo desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Modelo não encontrado!");
        }
    }
    @PutMapping("/ativar/{idModelo}")
    public ResponseEntity<?> ativar(
            @PathVariable Long idModelo
    ){
        try{
            this.modeloService.ativar(idModelo);
            return ResponseEntity.ok().body("Modelo ativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Modelo não encontrado!");
        }
    }

}