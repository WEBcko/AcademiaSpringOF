package br.com.webcko.academia.service;

import br.com.webcko.academia.DTOs.TreinoExercicioDTO;
import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.repository.ExercicioRepository;
import br.com.webcko.academia.repository.TreinoExercicioRepository;
import br.com.webcko.academia.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TreinoExercicioService {

    @Autowired
    private TreinoExercicioRepository treinoExercicioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    public Page<TreinoExercicio> listAll(Pageable pageable) {
        return this.treinoExercicioRepository.findAll(pageable);
    }


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final TreinoExercicioDTO treinoExercicioDto){

        final Treino treino = this.treinoRepository.findById(treinoExercicioDto.getIdTreino().getId()).orElse(null);

        final Exercicio exercicio = this.exercicioRepository.findById(treinoExercicioDto.getIdExercicio().getId()).orElse(null);

        Assert.isTrue(treino != null, "Treino nao encontrado");
        Assert.isTrue(exercicio !=null, "Exercicio nao Encontrado");

        Assert.isTrue(treinoExercicioDto.getDificuldade() !=null, " campo dificuldade vazio");
        Assert.isTrue( treinoExercicioDto.getPeso() != null, "Error campo PESO invalido ou vazio");
        Assert.isTrue(treinoExercicioDto.getSeries() != null, "Error campo REPETICOES vazio");

        TreinoExercicio treinoExercicio = new TreinoExercicio();

        treinoExercicio.setDificuldade(treinoExercicio.getDificuldade());
        treinoExercicio.setPeso(treinoExercicio.getPeso());
        treinoExercicio.setSeries(treinoExercicio.getSeries());

        treinoExercicio.setIdTreino(treino);
        treinoExercicio.setIdExercicio(exercicio);

        this.treinoExercicioRepository.save(treinoExercicio);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final TreinoExercicio treinoExercicio){

        final TreinoExercicio treinoExercicioBanco = this.treinoExercicioRepository.findById(id).orElse(null);

        Assert.isTrue(treinoExercicioBanco != null || treinoExercicioBanco.getId().equals(treinoExercicio.getId()), "Error registro nao encontrado");

        Assert.isTrue(treinoExercicio.getDificuldade() != null, "Campo DIFICULDADE vazio");
        Assert.isTrue( treinoExercicio.getPeso() != null, "Error PESO invalido ou vazio");
        Assert.isTrue(treinoExercicio.getSeries() != null, "Error campo REPETICOES vazio");


        final Treino treino = this.treinoRepository.findById(treinoExercicio.getIdTreino().getId()).orElse(null);
        final Exercicio exercicio = this.exercicioRepository.findById(treinoExercicio.getIdExercicio().getId()).orElse(null);

        Assert.isTrue(treino != null, "Treino nao encontrado");
        Assert.isTrue(exercicio !=null, "Exercicio nao Encontrado");

        this.treinoExercicioRepository.save(treinoExercicio);
    }


    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){
        final TreinoExercicio treinoExercicioBanco = this.treinoExercicioRepository.findById(id).orElse(null);

        Assert.isTrue(treinoExercicioBanco != null, "Error registro nao encontrado");

        this.treinoExercicioRepository.delete(treinoExercicioBanco);
    }




}
