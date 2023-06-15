package br.com.webcko.academia.controller;

import br.com.webcko.academia.DTOs.UsuarioRequest;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.entity.UsuarioRole;
import br.com.webcko.academia.repository.UsuarioRepository;
import br.com.webcko.academia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> findByIdParam(@RequestParam("id") final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        return usuario == null
          ? ResponseEntity.badRequest().body("Usuario nao encontrado")
          : ResponseEntity.ok(usuario);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastro (@RequestBody final Usuario request){
        //usuarioService.criarUsuario(request.getNome(), request.getSenha(), request.getRole());
        this.usuarioRepository.save(request);

        return  ResponseEntity.ok().body("deu");
    }

    @PutMapping
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final Usuario usuario){
        try{
            final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);

            if(usuarioBanco == null || !usuarioBanco.getId().equals(usuario.getId())){
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }

            this.usuarioRepository.save(usuario);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PutMapping("/{usuarioId}/role")
    public Usuario updateUsuarioRole(@PathVariable Long id, UsuarioRole novoRole){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuario.setRole(novoRole);
            return  usuarioRepository.save(usuario);
        }
        return null;
    }

}