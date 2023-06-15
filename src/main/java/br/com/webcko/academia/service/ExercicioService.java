package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.GrupoMuscular;
import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.repository.ExercicioRepository;
import br.com.webcko.academia.repository.GrupoMuscularRepository;
import br.com.webcko.academia.repository.TreinoExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;

    @Autowired
    private TreinoExercicioRepository treinoExercicioRepository;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Exercicio exercicio){

        Assert.isTrue(exercicio.getNome() != null, "Nome não informado");

        final GrupoMuscular grupo = this.grupoMuscularRepository.findById(exercicio.getId()).orElse(null);

        Assert.isTrue(grupo != null, "Grupo muscular nao encontrado");

        this.exercicioRepository.save(exercicio);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Exercicio exercicio){

        final Exercicio exercicioBanco = this.exercicioRepository.findById(id).orElse(null);
        Assert.isTrue(exercicioBanco != null || exercicio.getId().equals(id), "Registro não encontrado");

        Assert.isTrue(exercicio.getNome() != null, "Nome não informado");

        final GrupoMuscular grupo = this.grupoMuscularRepository.findById(exercicio.getId()).orElse(null);

        Assert.isTrue(grupo != null, "Grupo muscular nao encontrado");

        this.exercicioRepository.save(exercicio);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id) {

        final Exercicio exercicioBanco = this.exercicioRepository.findById(id).orElse(null);

        Assert.isTrue(exercicioBanco != null, "Registro não encontrado");

        final List<TreinoExercicio> treinos_exs = this.treinoExercicioRepository.findExercicio(exercicioBanco);



        if(treinos_exs.isEmpty()){
            this.exercicioRepository.delete(exercicioBanco);
        } else {
            exercicioBanco.setAtivo(false);
            this.exercicioRepository.save(exercicioBanco);
        }

    }

}