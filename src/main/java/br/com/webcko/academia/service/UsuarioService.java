package br.com.webcko.academia.service;

import br.com.webcko.academia.DTOs.UsuarioRequest;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.entity.UsuarioRole;
import br.com.webcko.academia.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Usuario> listAll(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void criarUsuario(UsuarioRequest request) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.getNome());
        novoUsuario.setEmail(request.getEmail());
        novoUsuario.setTelefone(request.getTelefone());
        String senhaCripto = passwordEncoder.encode(request.getSenha());
        novoUsuario.setSenha(senhaCripto);
        novoUsuario.setRole(UsuarioRole.CLIENTE);

        usuarioRepository.save(novoUsuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Usuario usuario){
        final Usuario usuarioBanco = this.usuarioRepository.findById(usuario.getId()).orElse(null);

        if (usuarioBanco != null) {
            usuarioBanco.setNome(usuario.getNome());
            usuarioBanco.setEmail(usuario.getEmail());
            usuarioBanco.setTelefone(usuario.getTelefone());
            usuarioBanco.setRole(usuario.getRole());
            usuarioBanco.setCpf(usuario.getCpf());

            if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
                String senhaCripto = passwordEncoder.encode(usuario.getSenha());
                usuarioBanco.setSenha(senhaCripto);
            }else{
                usuarioBanco.setSenha(usuarioBanco.getSenha());
            }


            this.usuarioRepository.save(usuarioBanco);
        } else {
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){
        final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);

        Assert.isTrue(usuarioBanco != null, "Registro não encontrado.");

        this.usuarioRepository.delete(usuarioBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario tipoUsuarioRole(Long id, UsuarioRole role){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuario.setRole(role);
            return  usuarioRepository.save(usuario);
        }
        return null;
    }

}