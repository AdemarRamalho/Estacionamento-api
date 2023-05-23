package Estacionamentopedro.Service;

import Estacionamentopedro.Entity.Configuracao;
import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Repository.ConfiguracaoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@NoArgsConstructor
public class ConfiguracaoService {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public Configuracao cadastrar(Configuracao configuracao) {
        return this.configuracaoRepository.save(configuracao);
    }


    @Transactional
    public void atualizar(Configuracao configuracao) {
        this.configuracaoRepository.save(configuracao);
    }

    public Configuracao findById(Long id) {
        Optional<Configuracao> configuracao = this.configuracaoRepository.findById(id);
        return configuracao.orElseThrow(() -> new RuntimeException("Configuracao não encontrado! Id: " + id));
    }
}