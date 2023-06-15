package br.com.webcko.academia.service;
import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.repository.EntradaSaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


@Service
public class EntradaSaidaService {

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @Transactional(rollbackFor =  Exception.class)
    public void cadastrar(final EntradaSaida entradaSaida){
        Assert.isTrue(entradaSaida.getCliente() == null, "Erro Campo Cliente vazio");
        Assert.isTrue(entradaSaida.getPersonal() == null, "Erro Campo Persoanl vazio");
        Assert.isTrue(entradaSaida.getHoraEntrada() == null, "Erro Campo HoraEntrada vazio");
        Assert.isTrue(entradaSaida.getHoraSaida() == null, "Erro Campo HoraSaida vazio");


        this.entradaSaidaRepository.save(entradaSaida);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final EntradaSaida entradaSaida){
        final EntradaSaida entradaSaidaBanco = this.entradaSaidaRepository.findById(id).orElse(null);

        Assert.isTrue(entradaSaidaBanco != null  || entradaSaidaBanco.getId().equals(entradaSaida.getId()),"Erro, registro nao encontrado");

        Assert.isTrue(entradaSaidaBanco.getId().equals(id), "erro da url e diferente do body");

        Assert.isTrue(entradaSaida.getCliente() == null, " erro no cliente campo vazio");
        Assert.isTrue(entradaSaida.getPersonal() == null, " erro no personal campo vazio");
        Assert.isTrue(entradaSaida.getHoraEntrada() == null, " erro no horaEntrada campo vazio");
        Assert.isTrue(entradaSaida.getHoraSaida() == null, " erro no horaSaida campo vazio");


        this.entradaSaidaRepository.save(entradaSaida);
    }

    @Transactional(rollbackFor = Exception.class)

    public void deletar(final Long id){
        final EntradaSaida entradaSaidaBanco = this.entradaSaidaRepository.findById(id).orElse(null);

        Assert.isTrue(entradaSaidaBanco != null, "Registro nao encontrado.");

        entradaSaidaBanco.setAtivo(false);
        this.entradaSaidaRepository.save(entradaSaidaBanco);

    }
}
