package Estacionamentopedro.Controller;


import Estacionamentopedro.Entity.Condutor;
import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/condutores")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        try{
            this.condutorService.findById(id);
            return ResponseEntity.ok().body(this.condutorService.findById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Id não encontrado!");
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        try{
            return ResponseEntity.ok(condutorService.listaCompleta());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @GetMapping("/lista/ativos")//
    public ResponseEntity<?> listaAtivos(){
        try{
            return ResponseEntity.ok(this.condutorService.listaCondutoresAtivos());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Nenhum condutor ativo no momento!");
        }
    }
    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody final Condutor condutor){
        try {
            return ResponseEntity.ok(condutorService.cadastrar(condutor));
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Condutor Já Cadastrada!");
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id,
                                    @Validated @RequestBody final Condutor condutor){
        Condutor condutorBanco = condutorService.findById(id);
        condutorBanco.setNome(condutor.getNome());
        condutorBanco.setTelefone(condutor.getTelefone());
        condutorBanco.setCpf(condutor.getCpf());

        try {
            this.condutorService.atualizar(condutorBanco);
            return ResponseEntity.ok("Condutor alterado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro ao alterar condutor!");
        }
    }


    @PutMapping("/desativar/{idCondutor}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idCondutor
    ){
        try{
            this.condutorService.desativar(idCondutor);
            return ResponseEntity.ok().body("Condutor desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Condutor não encontrado!");
        }
    }

    @PutMapping("/ativar/{idCondutor}")
    public ResponseEntity<?> ativar(
            @PathVariable Long idCondutor
    ){
        try{
            this.condutorService.ativar(idCondutor);
            return ResponseEntity.ok().body("Condutor ativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Nenhum condutor cadastrado!");
        }
    }
}