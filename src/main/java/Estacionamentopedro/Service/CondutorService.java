package Estacionamentopedro.Service;

import Estacionamentopedro.Components.ValidaCpf;
import Estacionamentopedro.Components.ValidarTelefone;
import Estacionamentopedro.Repository.CondutorRepository;
import Estacionamentopedro.Service.Exceptions.EntityNotFoundException;
import Estacionamentopedro.Entity.Condutor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        return this.condutorRepository.findAll();
    }

    public Condutor findById(Long id) {

        return this.condutorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id não encontrado: "+id));
    }
    @Transactional
    public void atualizar(Long id, Condutor condutor) {
        if(id == condutor.getId()) {
            this.condutorRepository.save(condutor);
        }else{
            throw new EntityNotFoundException("id não encontrado: "+id);
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
