package br.com.webcko.academia.controller;


import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.repository.TreinoRepository;
import br.com.webcko.academia.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/treino")
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private TreinoService treinoService;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){

        Treino treino = this.treinoRepository.findById(id).orElse(null);

        return treino == null
                ? ResponseEntity.badRequest().body("Nenhum registro encontrado")
                : ResponseEntity.ok(treino);

    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaTreino(){
        return ResponseEntity.ok(this.treinoRepository.findAll());
    }


    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Treino treino){
        try{
            this.treinoService.cadastrar(treino);
            return ResponseEntity.ok("Registro salvo com sucesso");
        }catch(Exception erro){
            return ResponseEntity.badRequest().body("Error" + erro.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar (@PathVariable("id") final Long id, @RequestBody final Treino treino){
        try{

            this.treinoService.editar(id,treino);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar (@RequestParam("id") final Long id) {

            try {
                this.treinoService.deletar(id);
                return ResponseEntity.ok().body("Registro deletado com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
            }

    }



}
