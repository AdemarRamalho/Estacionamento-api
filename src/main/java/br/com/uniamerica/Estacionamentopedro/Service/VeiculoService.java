package br.com.uniamerica.Estacionamentopedro.service;

import br.com.uniamerica.Estacionamentopedro.entity.Movimentacao;
import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import br.com.uniamerica.Estacionamentopedro.repository.VeiculoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {
            return this.veiculoRepository.save(veiculo);
    }
    public List<Veiculo> listaCompleta() {
        return this.veiculoRepository.findAll();
    }

    public Veiculo findById(Long id) {
        return this.veiculoRepository.findById(id).orElse(new Veiculo());
    }
    @Transactional
    public void atualizar(Long id, Veiculo veiculo) {
        if(id == veiculo.getId()) {
            this.veiculoRepository.save(veiculo);
        }
    }

    @Transactional
    public void desativar(Long id){
        var veiculo = this.veiculoRepository.findById(id);
        if (id == veiculo.get().getId()) {
            this.veiculoRepository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id){
        var veiculo = this.veiculoRepository.findById(id);
        if (id == veiculo.get().getId()) {
            this.veiculoRepository.ativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<Veiculo> listaVeiculosAtivos(){

        return this.veiculoRepository.VeiculosAtivos();
    }
}
