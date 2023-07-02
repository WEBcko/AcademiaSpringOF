package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Treino treino){
        Assert.isTrue(treino.getCodigoOrdem() == null,"Erro, campo vazio.");

        this.treinoRepository.save(treino);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Treino treino){
        final Treino treinoBanco = this.treinoRepository.findById(id).orElse(null);

        Assert.isTrue(treinoBanco != null || treino.getId().equals(id), "Registro não encontrado");

        Assert.isTrue(treino.getId() != null, "ID já existente.");
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){
        final Treino treinoBanco = this.treinoRepository.findById(id).orElse(null);

        Assert.isTrue(treinoBanco != null, "Registro não encontrado");

        this.treinoRepository.delete(treinoBanco);
    }
}
