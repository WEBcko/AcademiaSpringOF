package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.TreinoExercicio;
import br.com.webcko.academia.repository.TreinoExercicioRepository;
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

    public Page<TreinoExercicio> listAll(Pageable pageable) {
        return this.treinoExercicioRepository.findAll(pageable);
    }


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final TreinoExercicio treinoExercicio){
        Assert.isTrue(treinoExercicio.getIdTreino() == null,"Error campo TREINO vazio");
        Assert.isTrue(treinoExercicio.getIdExercicio() == null, "Error  campo EXERCICIO vazio");
        Assert.isTrue(treinoExercicio.getDificuldade().isBlank(), "Campo DIFICULDADE vazio");
        Assert.isTrue( treinoExercicio.getPeso() != null, "Error campo PESO invalido ou vazio");
        Assert.isTrue(treinoExercicio.getSeries() != null, "Error campo REPETICOES vazio");

        this.treinoExercicioRepository.save(treinoExercicio);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final TreinoExercicio treinoExercicio){
        final TreinoExercicio treinoExercicioBanco = this.treinoExercicioRepository.findById(id).orElse(null);

        Assert.isTrue(treinoExercicioBanco != null || treinoExercicioBanco.getId().equals(treinoExercicio.getId()), "Error registro nao encontrado");

        Assert.isTrue(treinoExercicio.getId().equals(id), "id da URL e difirente do body");

        Assert.isTrue(treinoExercicio.getIdTreino() == null,"Error campo TREINO vazio");
        Assert.isTrue(treinoExercicio.getIdExercicio() == null, "Error  campo EXERCICIO vazio");
        Assert.isTrue(treinoExercicio.getDificuldade().isBlank(), "Campo DIFICULDADE vazio");
        Assert.isTrue( treinoExercicio.getPeso() != null, "Error PESO invalido ou vazio");
        Assert.isTrue(treinoExercicio.getSeries() != null, "Error campo REPETICOES vazio");

        this.treinoExercicioRepository.save(treinoExercicio);

    }


    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){
        final TreinoExercicio treinoExercicioBanco = this.treinoExercicioRepository.findById(id).orElse(null);

        Assert.isTrue(treinoExercicioBanco != null, "Error registro nao encontrado");

        this.treinoExercicioRepository.delete(treinoExercicioBanco);
    }




}
