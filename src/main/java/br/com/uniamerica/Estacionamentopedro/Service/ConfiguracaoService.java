package br.com.uniamerica.Estacionamentopedro.service;

import br.com.uniamerica.Estacionamentopedro.entity.Configuracao;
import br.com.uniamerica.Estacionamentopedro.repository.ConfiguracaoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
public class ConfiguracaoService {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public void atualizar(Long id, Configuracao configuracao) {
        if(id == configuracao.getId()) {
            this.configuracaoRepository.save(configuracao);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public Configuracao cadastrar(Configuracao configuracao) {
        return this.configuracaoRepository.save(configuracao);
    }

    public Configuracao findById(Long id) {
        return this.configuracaoRepository.findById(id).orElse(new Configuracao());
    }
}
