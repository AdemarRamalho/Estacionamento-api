package Estacionamentopedro.Service;

import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Entity.Modelo;
import Estacionamentopedro.Repository.ModeloRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional
    public Modelo cadastrar(Modelo modelo) {
        if(modelo.getNome().trim().isEmpty()){
            throw  new RuntimeException("Erro modelo Nulo!");
        }if(modelo.getNome().length() < 3){
            throw new DataIntegrityViolationException("Minimo de 3 Caracteres para o modelo e/ou maximo de 50!");
        }
        else{
            return this.modeloRepository.save(modelo);
        }
    }


    public List<Modelo> listaCompleta() {
        return this.modeloRepository.findAll();
    }

    public Modelo findById(Long id) {
        Optional<Modelo> modelo = this.modeloRepository.findById(id);
        return modelo.orElseThrow(() -> new RuntimeException("Modelo não encontrado! Id: " + id));
    }

    @Transactional
    public void atualizar(final Modelo modelo) {
        this.modeloRepository.save(modelo);
    }

    @Transactional
    public void desativar(Long id){
        var marca = this.modeloRepository.findById(id);
        if (id == marca.get().getId()) {
            this.modeloRepository.desativar(id);
        }
        else {
            throw new RuntimeException("Modelo nao encontrado!");
        }
    }

    public List<Modelo> listaModelosAtivos(){
        return this.modeloRepository.modelosAtivos();
    }



    @Transactional
    public void ativar(Long id){
        var modelo = this.modeloRepository.findById(id);
        if (id == modelo.get().getId()) {
            this.modeloRepository.ativar(id);
        }
        else {
            throw new RuntimeException("Modelo  nao encontrado!");
        }
    }
}