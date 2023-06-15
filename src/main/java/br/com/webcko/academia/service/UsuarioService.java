package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.entity.UsuarioRole;
import br.com.webcko.academia.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public Usuario buscarPorId(final Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    @Transactional(rollbackFor = Exception.class)
    public void criarUsuario(String nome, String senha, UsuarioRole role){

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setRole(role);

        this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Usuario usuario){
        final Usuario usuarioBanco = this.usuarioRepository.findById(usuario.getId()).orElse(null);

        this.usuarioRepository.save(usuario);
    }
    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Usuario usuario){
        final Usuario usuarioBanco = this.usuarioRepository.findById(usuario.getId()).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario tipoUsuarioRole(Long id, UsuarioRole novoRole){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuario.setRole(novoRole);
            return  usuarioRepository.save(usuario);
        }
        return null;
    }
}