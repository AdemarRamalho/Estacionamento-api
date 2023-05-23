package Estacionamentopedro.Controller;

import Estacionamentopedro.Configuracao.ExceptionHandlerAdvice;
import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        try{
            this.marcaService.findById(id);
            return ResponseEntity.ok().body(this.marcaService.findById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Id não encontrado!");
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        try{
            return ResponseEntity.ok(this.marcaService.listaCompleta());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Lista de Marcas indisponivel!");
        }
    }

    @GetMapping("/lista/ativos")//
    public ResponseEntity<?> listaAtivos(){
        try{
            return ResponseEntity.ok(this.marcaService.listaMarcasAtivos());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Nenhuma marca ativa no momento!");
        }
    }

    @PostMapping//Cadastrar ok VALIDADO!
    public ResponseEntity<?> cadastrar(@Validated @RequestBody final Marca marca){
        try {
            return ResponseEntity.ok(marcaService.cadastrar(marca));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Marca Já Cadastrada!");
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id,
                                    @Validated @RequestBody final Marca marca){
        Marca marcaBanco = marcaService.findById(id);
        marcaBanco.setNome(marca.getNome());

        try {
            this.marcaService.atualizar(marcaBanco);
            return ResponseEntity.ok("Marca alterada com sucesso!");
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Erro nenhum cadastrado!");
        }
    }

    @PutMapping("/desativar/{idMarca}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idMarca
    ){
        try{
            this.marcaService.desativar(idMarca);
            return ResponseEntity.ok().body("Marca desativada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Marca não encontrada!");
        }
    }

    @PutMapping("/ativar/{idMarca}")
    public ResponseEntity<?> ativar(
            @PathVariable Long idMarca
    ){
        try{
            this.marcaService.ativar(idMarca);
            return ResponseEntity.ok().body("Marca ativada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Nenhuma marca cadastrado!");
        }
    }
}