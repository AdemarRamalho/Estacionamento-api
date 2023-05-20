package br.com.uniamerica.Estacionamentopedro.service;

import br.com.uniamerica.Estacionamentopedro.entity.Marca;
import br.com.uniamerica.Estacionamentopedro.entity.Modelo;
import br.com.uniamerica.Estacionamentopedro.entity.Movimentacao;
import br.com.uniamerica.Estacionamentopedro.repository.MarcaRepository;
import br.com.uniamerica.Estacionamentopedro.repository.MovimentacaoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Movimentacao cadastrar(Movimentacao movimentacao) {
        return this.movimentacaoRepository.save(movimentacao);
    }


    public List<Movimentacao> listaCompleta() {
        return this.movimentacaoRepository.findAll();
    }

    public Movimentacao findById(Long id) {
        return this.movimentacaoRepository.findById(id).orElse(new Movimentacao());
    }
    @Transactional
    public void atualizar(Long id, Movimentacao movimentacao) {
        if(id == movimentacao.getId()) {
            this.movimentacaoRepository.save(movimentacao);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void desativar(Long id){
        var movimentacao = this.movimentacaoRepository.findById(id);
        if (id == movimentacao.get().getId()) {
            this.movimentacaoRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void ativar(Long id){
        var movimentacao = this.movimentacaoRepository.findById(id);
        if (id == movimentacao.get().getId()) {
            this.movimentacaoRepository.ativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<Movimentacao> listaMovimentacoesAtivas(){
        return this.movimentacaoRepository.MovimentacoesAtivas();
    }

    public void ControleMovimentacaoEntrada(){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setEntrada(LocalDateTime.now());
        int horas,minutos,segundos,dias;

        System.out.println(movimentacao.getEntrada());


        dias = movimentacao.getEntrada().getDayOfMonth();
        horas = movimentacao.getEntrada().getHour();
        minutos = movimentacao.getEntrada().getMinute();
        segundos = movimentacao.getEntrada().getSecond();



        if(minutos > 60){
            horas = horas + 1;
            minutos =0;
        }

        System.out.println("Dia: " + dias);
        System.out.println("Hora: " + horas);
        System.out.println("Minuto: " + minutos);
        System.out.println("Segundo: " + segundos);

        System.out.println("Saida");

        int horasSaida,minutosSaida,segundosSaida,diasSaida;

        movimentacao.setSaida(LocalDateTime.now());

        diasSaida = movimentacao.getSaida().getDayOfMonth();
        horasSaida = movimentacao.getSaida().getHour();
        minutosSaida = movimentacao.getSaida().getMinute();
        segundosSaida = movimentacao.getSaida().getSecond();

        horasSaida = horasSaida +3;

        minutosSaida = minutosSaida + 45;

        if(minutosSaida > 59){
            horasSaida = horasSaida + 1;
            minutosSaida =0;
        }

        int horasfinais = horasSaida - horas;
        int minutosfinais = minutosSaida - minutos;
        System.out.println("Horas: " + horasfinais + " Minutos: "+ minutosfinais);
    }


}
