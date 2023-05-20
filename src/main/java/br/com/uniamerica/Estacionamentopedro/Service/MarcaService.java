package br.com.uniamerica.Estacionamentopedro.service;

import br.com.uniamerica.Estacionamentopedro.entity.Condutor;
import br.com.uniamerica.Estacionamentopedro.entity.Marca;
import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import br.com.uniamerica.Estacionamentopedro.repository.MarcaRepository;
import br.com.uniamerica.Estacionamentopedro.repository.VeiculoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@NoArgsConstructor
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public Marca cadastrar(Marca marca) {
        if(marca.getNome().trim().isEmpty()){
            throw  new RuntimeException("Erro marca Nula!");
        }else{
            return this.marcaRepository.save(marca);
        }
    }


    public List<Marca> listaCompleta() {
        return this.marcaRepository.findAll();
    }

    public Marca findById(Long id) {
        return this.marcaRepository.findById(id).orElse(new Marca());
    }
    @Transactional
    public void atualizar(Long id, Marca marca) {
        if(id == marca.getId()) {
            this.marcaRepository.save(marca);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void desativar(Long id){
        var marca = this.marcaRepository.findById(id);
        if (id == marca.get().getId()) {
            this.marcaRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void ativar(Long id){
        var marca = this.marcaRepository.findById(id);
        if (id == marca.get().getId()) {
            this.marcaRepository.ativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<Marca> listaMarcasAtivos(){
        return this.marcaRepository.MarcasAtivas();
    }
}
