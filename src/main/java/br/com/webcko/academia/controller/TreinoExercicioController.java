package br.com.webcko.academia.controller;


import br.com.webcko.academia.DTOs.TreinoExercicioDTO;
import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.repository.TreinoExercicioRepository;
import br.com.webcko.academia.service.TreinoExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/treinoexercicios")
public class TreinoExercicioController {

    @Autowired
    private TreinoExercicioRepository treinoExercicioRepository;

    @Autowired
    private TreinoExercicioService treinoExercicioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByid (@PathVariable("id") final Long id){
        try{
            return ResponseEntity.ok(this.treinoExercicioRepository.findById(id));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @GetMapping("/role")
    public ResponseEntity<Page<TreinoExercicio>> getAllRequest(Pageable pageable) {
        return ResponseEntity.ok(this.treinoExercicioService.listAll(pageable));

    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaTreinoExercicio(){
        return ResponseEntity.ok(this.treinoExercicioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final TreinoExercicioDTO treinoExercicio){
        try{
            System.out.println("EXERCICIO");
            System.out.println(treinoExercicio.getIdExercicio());
            System.out.println("TREINO");
            System.out.println(treinoExercicio.getIdTreino());
            System.out.println("Series");
            System.out.println(treinoExercicio.getSeries());
            System.out.println("dificuldade");
            System.out.println(treinoExercicio.getDificuldade());
            System.out.println("peso");
            System.out.println(treinoExercicio.getPeso());

            this.treinoExercicioService.cadastrar(treinoExercicio);
            return ResponseEntity.ok("Cadastrado");
        }catch(RuntimeException e){
            return  ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final TreinoExercicio treinoExercicio){
        try{
            this.treinoExercicioService.editar(id,treinoExercicio);
            return ResponseEntity.ok("Alterado com sucesso");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        try{
            this.treinoExercicioService.deletar(id);
            return ResponseEntity.ok("Registro deletado");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }


}
