package Estacionamentopedro.Controller;


import Estacionamentopedro.Entity.Condutor;
import Estacionamentopedro.Service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/condutores")
public class                                                                                                                                                                                                        CondutorController {

    @Autowired
    private CondutorService condutorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Condutor condutor = this.condutorService.findById(id);
        return condutor == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(condutor);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.condutorService.listaCompleta());
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        return ResponseEntity.ok(this.condutorService.listaCondutoresAtivos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Condutor condutor){
        try{
            this.condutorService.cadastrar(condutor);
            return ResponseEntity.ok().body("Sucesso!, Condutor Cadastrado!");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idCondutor}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long idCondutor,
            @RequestBody Condutor condutor
    ) {
            this.condutorService.atualizar(idCondutor, condutor);
            return ResponseEntity.ok().body("Condutor atualizado com sucesso!");
    }


    @PutMapping("/desativar/{idCondutor}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idCondutor
    ){
            this.condutorService.desativar(idCondutor);
            return ResponseEntity.ok().body("Condutor desativado com sucesso!");
    }

    @PutMapping("/ativar/{idCondutor}")
    public ResponseEntity<?> ativar(
            @PathVariable Long idCondutor
    ){
        try{
            this.condutorService.ativar(idCondutor);
            return ResponseEntity.ok().body("Marca ativada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
