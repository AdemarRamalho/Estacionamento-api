package Estacionamentopedro.Service;

import Estacionamentopedro.Entity.Movimentacao;
import Estacionamentopedro.Repository.MovimentacaoRepository;
import Estacionamentopedro.Entity.Condutor;
import Estacionamentopedro.Entity.Configuracao;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Movimentacao cadastrar(Movimentacao movimentacao) {
//        movimentacao.setEntradaMov();
        return this.movimentacaoRepository.save(movimentacao);
    }


    public List<Movimentacao> listaCompleta() {
        return this.movimentacaoRepository.findAll();
    }

    public Movimentacao findById(Long id) {
        Optional<Movimentacao> movimentacao = this.movimentacaoRepository.findById(id);
        return movimentacao.orElseThrow(() -> new RuntimeException("Movimentacao não encontrada! Id: " + id));
    }

    @Transactional
    public void atualizar(Movimentacao movimentacao) {
        this.movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void desativar(Long id) {
        var movimentacao = this.movimentacaoRepository.findById(id);
        if (id == movimentacao.get().getId()) {
            this.movimentacaoRepository.desativar(id);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void ativar(Long id) {
        var movimentacao = this.movimentacaoRepository.findById(id);
        if (id == movimentacao.get().getId()) {
            this.movimentacaoRepository.ativar(id);
        } else {
            throw new RuntimeException();
        }
    }

    public List<Movimentacao> listaMovimentacoesAtivas() {
        return this.movimentacaoRepository.MovimentacoesAtivas();
    }


    @Transactional
    public void finalizaMov(final Long id, final Movimentacao movimentacao){
        //movimentacao.setSaida(LocalDateTime.now());
        Condutor condutor = new Condutor();
        this.movimentacaoRepository.save(movimentacao);
        Configuracao configuracao = movimentacaoRepository.buscaConfig();

        LocalDateTime saida = movimentacao.getSaida();

        LocalTime inicioExpediente = configuracao.getInicioExpediente();
        LocalTime fimExpediente = configuracao.getFimExpediente();

        //CALCULANDO AS HORAS E MINUTOS
        Long tempoPermanencia = Duration.between(movimentacao.getEntrada(), movimentacao.getSaida()).getSeconds();
        Long minutos = tempoPermanencia / 60;
        Long minutosPermanenciaSemConversao = tempoPermanencia/60;
        Long horas = minutos / 60;
        minutos = minutos % 60;
        movimentacao.setTempoHoras(horas);
        movimentacao.setTempoMinutos(minutos);

        //CALCULANDO VALOR DAS HORAS
        BigDecimal vHora = configuracao.getValorHora();
        BigDecimal vTotalHora = BigDecimal.valueOf(horas).multiply(vHora);
        movimentacao.setValorHora(vTotalHora);
        System.out.println(vTotalHora);

        int multa = 0;

        if (fimExpediente.isBefore(movimentacao.getSaida().toLocalTime())){//Calculo para verificar se está depois do horario de funcionamento e gerar multa

            multa += (int) Duration.between(fimExpediente,saida.toLocalTime()).toMinutes();
            System.out.println("Testando");
            System.out.println(multa);

            System.out.println("Valor da multa:  " + multa);
        }

        int valorFinalMulta = configuracao.getValorMinutoMulta().intValue();//PEGANDO O VALOR DA MULTA POR MINUTO
        int valorFinalHora = movimentacao.getValorHora().intValue(); // PEGANDO O VALOR FINAL DO CALCULO DE HORAS JA FEITO
        System.out.println(valorFinalMulta);

        movimentacao.setValorHoraMulta(configuracao.getValorMinutoMulta()); // Setando o valor do minuto da multa

        movimentacao.setValorMulta(BigDecimal.valueOf(multa * valorFinalMulta)); // SETANDO O VALOR DA MULTA

        int calculoMulta = (multa * valorFinalMulta) + valorFinalHora;

        System.out.println("Valor total da sua estadia no estacionamento: " + calculoMulta);
        movimentacao.setValorTotal((long) calculoMulta);

        int valorMultaPorHora = configuracao.getValorMinutoMulta().intValue();
        valorMultaPorHora = valorMultaPorHora * 60;
        movimentacao.setValorHoraMulta(BigDecimal.valueOf(valorMultaPorHora));


        //Armazenando as horas pagas no condutor!
        int armazenarHorasCondutor = multa/60;
        armazenarHorasCondutor = (int) (armazenarHorasCondutor + movimentacao.getTempoHoras());
        movimentacao.getCondutor().setTempoPago((long) armazenarHorasCondutor);

    }
}