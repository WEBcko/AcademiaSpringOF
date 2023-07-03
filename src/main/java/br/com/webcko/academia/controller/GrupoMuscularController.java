package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.GrupoMuscular;
import br.com.webcko.academia.repository.GrupoMuscularRepository;
import br.com.webcko.academia.service.GrupoMuscularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/grupo")
public class GrupoMuscularController {

    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;

    @Autowired
    private GrupoMuscularService grupoMuscularService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){

        GrupoMuscular grupo = this.grupoMuscularRepository.findById(id).orElse(null);

        return grupo == null
                ? ResponseEntity.badRequest().body("Nenhum registro encontrado")
                : ResponseEntity.ok(grupo);

    }

    @GetMapping("/lista")
    public ResponseEntity<?> lista(){
        return ResponseEntity.ok(this.grupoMuscularRepository.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> buscarAtivos (){
        try{
            return ResponseEntity.ok(this.grupoMuscularRepository.findByAtivos());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final GrupoMuscular grupo){
        try{
            this.grupoMuscularService.cadastrar(grupo);
            return ResponseEntity.ok(grupo);
        }catch(Exception error){
            return ResponseEntity.badRequest().body("Error" + error.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final GrupoMuscular grupo){
        try{
            this.grupoMuscularService.editar(id, grupo);
            return ResponseEntity.ok(grupo);
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@PathVariable("id") final Long id) {
        try {
            this.grupoMuscularService.deletar(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso");

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
    }



}
