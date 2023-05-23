package Estacionamentopedro.Service;

import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Entity.Veiculo;
import Estacionamentopedro.Repository.VeiculoRepository;
import lombok.NoArgsConstructor;
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
    public Veiculo cadastrar(final Veiculo veiculo) {
        if(veiculo.getAno() > 2023 || veiculo.getAno() < 1900){
            throw new RuntimeException("Erro ano tem que ser valido!");
        }else{
            return this.veiculoRepository.save(veiculo);
        }
    }
    public List<Veiculo> listaCompleta() {
        return this.veiculoRepository.findAll();
    }

    public Veiculo findById(Long id) {

        Optional<Veiculo> veiculo = this.veiculoRepository.findById(id);
        return veiculo.orElseThrow(() -> new RuntimeException("Veiculo não encontrado! Id: " + id));

    }
    @Transactional
    public void atualizar(final Veiculo veiculo) {
        if(veiculo.getAno() > 2023 || veiculo.getAno() < 1900){
            throw new RuntimeException("Erro ano tem que ser valido!");
        }else{
            this.veiculoRepository.save(veiculo);
        }
    }


    @Transactional
    public void desativar(Long id){
        var veiculo = this.veiculoRepository.findById(id);
        if (id == veiculo.get().getId()) {
            this.veiculoRepository.desativar(id);
        }else{
            throw  new RuntimeException("Erro ao Desativar!");
        }
    }

    @Transactional
    public void ativar(Long id){
        var veiculo = this.veiculoRepository.findById(id);
        if (id == veiculo.get().getId()) {
            this.veiculoRepository.ativar(id);
        }
        else {
            throw new RuntimeException("Erro ao ativar!");
        }
    }

    public List<Veiculo> listaVeiculosAtivos(){

        return this.veiculoRepository.VeiculosAtivos();
    }
}