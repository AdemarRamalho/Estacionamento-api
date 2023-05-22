package Estacionamentopedro.Service;

import Estacionamentopedro.Repository.ModeloRepository;
import Estacionamentopedro.Entity.Modelo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional
    public Modelo cadastrar(Modelo modelo) {
        return this.modeloRepository.save(modelo);
    }


    public List<Modelo> listaCompleta() {
        return this.modeloRepository.findAll();
    }

    public Modelo findById(Long id) {
        return this.modeloRepository.findById(id).orElse(new Modelo());
    }
    @Transactional
    public void atualizar(Long id, Modelo modelo) {
        if(id == modelo.getId()) {
            this.modeloRepository.save(modelo);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void desativar(Long id){
        var marca = this.modeloRepository.findById(id);
        if (id == marca.get().getId()) {
            this.modeloRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
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
            throw new RuntimeException();
        }
    }
}
