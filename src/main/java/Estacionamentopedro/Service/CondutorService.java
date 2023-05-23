package Estacionamentopedro.Service;

import Estacionamentopedro.Components.ValidaCpf;
import Estacionamentopedro.Components.ValidarTelefone;
import Estacionamentopedro.Entity.Condutor;
import Estacionamentopedro.Entity.Marca;
import Estacionamentopedro.Repository.CondutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class CondutorService {
    @Autowired
    private CondutorRepository condutorRepository;

    @Transactional
    public Condutor cadastrar(Condutor condutor) {
        if(condutor.getNome().trim().isBlank() || ValidaCpf.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf invalido: " + condutor.getCpf());
        }else if(ValidarTelefone.telefoneValido(condutor.getTelefone()) == false){
            throw new RuntimeException("Telefone invalido: " + condutor.getTelefone());
        }
        else {
            return this.condutorRepository.save(condutor);
        }
    }


    public List<Condutor> listaCompleta() {
        if (condutorRepository.findAll().isEmpty()) {
            throw new RuntimeException(", banco de dados não possui condutores cadastrados!");

        } else {
            return condutorRepository.findAll();
        }

    }

    public Condutor findById(Long id) {
        Optional<Condutor> condutor = this.condutorRepository.findById(id);
        return condutor.orElseThrow(() -> new RuntimeException("Condutor não encontrado! Id: " + id));
    }
    @Transactional
    public void atualizar(Condutor condutor) {
        try{
            this.condutorRepository.save(condutor);
        }catch (RuntimeException e){
            throw  new RuntimeException("Erro Condutor não existe!");
        }
    }

    @Transactional
    public void desativar(Long id){
        var condutor = this.condutorRepository.findById(id);
        if (id == condutor.get().getId()) {
            this.condutorRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void ativar(Long id){
        var condutor = this.condutorRepository.findById(id);
        if (id == condutor.get().getId()) {
            this.condutorRepository.ativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<Condutor> listaCondutoresAtivos(){
        return this.condutorRepository.CondutoresAtivos();
    }
}