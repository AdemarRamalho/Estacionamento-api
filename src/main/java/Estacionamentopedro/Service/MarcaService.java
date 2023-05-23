package Estacionamentopedro.Service;

import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Repository.MarcaRepository;
import lombok.NoArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public Marca cadastrar(Marca marca) {
        if(marca.getNome().trim().isEmpty()){
            throw  new RuntimeException("Erro marca Nula!");
        }if(marca.getNome().length() < 3){
            throw new DataIntegrityViolationException("Minimo de 3 Caracteres para a marca e/ou maximo de 50!");
        }
        else{
            return this.marcaRepository.save(marca);
        }
    }

    public List<Marca> listaCompleta() {
        return this.marcaRepository.findAll();
    }

    public Marca findById(Long id) {
        Optional<Marca> marca = this.marcaRepository.findById(id);
        return marca.orElseThrow(() -> new RuntimeException("Marca não encontrado! Id: " + id));
    }
    @Transactional
    public void atualizar(final Marca marca) {

        this.marcaRepository.save(marca);
    }

    @Transactional
    public void desativar(Long id){
        var marca = this.marcaRepository.findById(id);
        if (id == marca.get().getId()) {
            this.marcaRepository.desativar(id);
        }
        else {
            throw new RuntimeException("Erro ao desativar!");
        }
    }

    @Transactional
    public void ativar(Long id){
        var marca = this.marcaRepository.findById(id);
        if (id == marca.get().getId()) {
            this.marcaRepository.ativar(id);
        }
        else {
            throw new RuntimeException("Erro ao ativar!");
        }
    }

    public List<Marca> listaMarcasAtivos(){
        return this.marcaRepository.MarcasAtivas();
    }
}