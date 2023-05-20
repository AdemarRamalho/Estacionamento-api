package br.com.uniamerica.Estacionamentopedro.controller;

import br.com.uniamerica.Estacionamentopedro.entity.Marca;
import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import br.com.uniamerica.Estacionamentopedro.service.MarcaService;
import br.com.uniamerica.Estacionamentopedro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Marca marca = this.marcaService.findById(id);
        return marca == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(marca);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.marcaService.listaCompleta());
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        return ResponseEntity.ok(this.marcaService.listaMarcasAtivos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Marca marca){
        try {
            return ResponseEntity.ok(marcaService.cadastrar(marca));
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{idMarca}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long idMarca,
            @RequestBody Marca marca
    ) {
        try {
            this.marcaService.atualizar(idMarca, marca);
            return ResponseEntity.ok().body("Marca atualizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
            return ResponseEntity.badRequest().body(e.getMessage());
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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
