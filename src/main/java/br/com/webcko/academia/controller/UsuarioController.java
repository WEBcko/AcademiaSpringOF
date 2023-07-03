package br.com.webcko.academia.controller;

import br.com.webcko.academia.DTOs.UsuarioRequest;
//import br.com.webcko.academia.components.TokenValidationService;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.entity.UsuarioRole;
import br.com.webcko.academia.repository.UsuarioRepository;
import br.com.webcko.academia.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
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

//    @Autowired
//    private TokenValidationService tokenValidationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdParam(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        return usuario == null
          ? ResponseEntity.badRequest().body("Usuario nao encontrado")
          : ResponseEntity.ok(usuario);
    }

//    @GetMapping("/lista")
//    public ResponseEntity<?> listaCompleta(HttpServletRequest request){
//        String token = tokenValidationService.getTokenFromRequest(request);
//
//        if (token != null && tokenValidationService.validateToken(token)) {
//            // Token válido, pode prosseguir com a lógica do método
//
//            return ResponseEntity.ok(this.usuarioRepository.findAll());
//        } else {
//            // Token inválido ou ausente, retornar uma resposta de erro
//
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou ausente");
//        }
//    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){

        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastro (@RequestBody final UsuarioRequest request){
        usuarioService.criarUsuario(request);

        return ResponseEntity.ok().body("Usuario cadastrado com sucesso.");
    }

    @PutMapping
    public ResponseEntity<?> editar (@PathVariable("id") final Long id, @RequestBody final Usuario usuario){
        try {
            usuarioService.editar(id, usuario);
            return ResponseEntity.ok("Registro atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o registro.");
        }
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<?> alterarUsuarioRole(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        Usuario usuarioAtualizado = usuarioService.tipoUsuarioRole(id, usuario.getRole());

        if (usuarioAtualizado != null) {
            return ResponseEntity.ok().body("Role do usuário atualizado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") final Long id) {
        try {
            this.usuarioService.deletar(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso");

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
    }
}