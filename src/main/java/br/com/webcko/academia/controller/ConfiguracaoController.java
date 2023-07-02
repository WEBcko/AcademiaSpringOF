package br.com.webcko.academia.controller;

import br.com.webcko.academia.entity.Configuracao;
import br.com.webcko.academia.repository.ConfiguracaoRepository;
import br.com.webcko.academia.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping
    public ResponseEntity<?> findConfiguracao (){
        return ResponseEntity.ok(this.configuracaoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Configuracao configuracao){
        try{
            this.configuracaoService.cadastrar(configuracao);
            return ResponseEntity.ok().body("Configuacao salva com sucesso");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final Configuracao configuracao){
        try{
           this.configuracaoService.editar(id,configuracao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Error "  + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }



    }

}
