package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.repository.ExercicioRepository;
import br.com.webcko.academia.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){

        Exercicio exercicio = this.exercicioRepository.findById(id).orElse(null);

        return exercicio == null
                ? ResponseEntity.badRequest().body("Nenhum registro encontrado")
                : ResponseEntity.ok(exercicio);

    }

    @GetMapping("/role")
    public ResponseEntity<Page<Exercicio>> getAllRequest(Pageable pageable) {
        return ResponseEntity.ok(this.exercicioService.listAll(pageable));

    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaExercicio(){
        return ResponseEntity.ok(this.exercicioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Exercicio exercicio){
        try{
            this.exercicioService.cadastrar(exercicio);
            return ResponseEntity.ok("Registro salvo com sucesso");
        }catch(Exception erro){
            return ResponseEntity.badRequest().body("Error" + erro.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar (@PathVariable("id") final Long id, @RequestBody final Exercicio exercicio){
        try{

            this.exercicioService.editar(id,exercicio);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException erro){
            return ResponseEntity.internalServerError().body("Error" + erro.getCause().getCause().getMessage());
        }catch(RuntimeException erro){
            return ResponseEntity.badRequest().body("Error " + erro.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@RequestParam("id") final Long id) {

        try {
            this.exercicioService.deletar(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
    }
}

