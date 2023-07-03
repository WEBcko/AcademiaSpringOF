package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.GrupoMuscular;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.repository.ExercicioRepository;
import br.com.webcko.academia.repository.GrupoMuscularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class GrupoMuscularService {

    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    public Page<GrupoMuscular> listAll(Pageable pageable) {
        return this.grupoMuscularRepository.findAll(pageable);
    }


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final GrupoMuscular grupo){

        Assert.isTrue(grupo.getNome() != null, "Nome não informado");

        this.grupoMuscularRepository.save(grupo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final GrupoMuscular grupo){

        final GrupoMuscular grupoBanco = this.grupoMuscularRepository.findById(id).orElse(null);

        Assert.isTrue(grupoBanco != null || grupo.getId().equals(id), "Registro não encontrado");

        Assert.isTrue(grupo.getNome() != null, "Nome não informado");

        this.grupoMuscularRepository.save(grupo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id) {

        final GrupoMuscular grupoBanco = this.grupoMuscularRepository.findById(id).orElse(null);

        Assert.isTrue(grupoBanco != null, "Registro não encontrado");

        final List<Exercicio> exercicios = this.exercicioRepository.findExerciciosByGrupo(grupoBanco);

        if(exercicios.isEmpty()){
            this.grupoMuscularRepository.delete(grupoBanco);
        } else {
            grupoBanco.setAtivo(false);
            this.grupoMuscularRepository.save(grupoBanco);
        }

    }


}
