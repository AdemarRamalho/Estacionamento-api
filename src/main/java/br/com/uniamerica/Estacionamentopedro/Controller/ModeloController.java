package br.com.uniamerica.Estacionamentopedro.controller;


import br.com.uniamerica.Estacionamentopedro.entity.Marca;
import br.com.uniamerica.Estacionamentopedro.entity.Modelo;
import br.com.uniamerica.Estacionamentopedro.service.MarcaService;
import br.com.uniamerica.Estacionamentopedro.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/modelos")
public class ModeloController {
    @Autowired
    private ModeloService modeloService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Modelo modelo = this.modeloService.findById(id);
        return modelo == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(modelo);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.modeloService.listaCompleta());
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        return ResponseEntity.ok(this.modeloService.listaModelosAtivos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Modelo modelo){
        try {
            return ResponseEntity.ok(modeloService.cadastrar(modelo));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{idModelo}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long idModelo,
            @RequestBody Modelo modelo
    ) {
        try {
            this.modeloService.atualizar(idModelo, modelo);
            return ResponseEntity.ok().body("Modelo atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
            return ResponseEntity.badRequest().body(e.getMessage());
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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
