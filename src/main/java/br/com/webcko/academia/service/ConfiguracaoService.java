package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Configuracao;
import br.com.webcko.academia.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ConfiguracaoService {


    @Autowired
    private ConfiguracaoRepository configuracaoRepository;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Configuracao configuracao){
        Assert.isTrue(configuracao.getHoraAbrir() == null, "Error, campo vazio");
        Assert.isTrue(configuracao.getHoraFechar() == null, "Error, campo vazio");
        Assert.isTrue(configuracao.getOcupacao() == null, "Error, campo vazio");

        this.configuracaoRepository.save(configuracao);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Configuracao configuracao){
        final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);

        Assert.isTrue(configuracaoBanco != null || configuracaoBanco.getId().equals(configuracao.getId()), "Error, registro nao encontrado");

        Assert.isTrue(configuracao.getId().equals(id), "Error id da URL e diferente do body");

        Assert.isTrue(configuracao.getHoraAbrir() == null, "Error, campo vazio");
        Assert.isTrue(configuracao.getHoraFechar() == null, "Error, campo vazio");
        Assert.isTrue(configuracao.getOcupacao() == null, "Error, campo vazio");


        this.configuracaoRepository.save(configuracao);
    }
}
