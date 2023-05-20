package br.com.uniamerica.Estacionamentopedro;

import br.com.uniamerica.Estacionamentopedro.entity.Movimentacao;
import br.com.uniamerica.Estacionamentopedro.service.MovimentacaoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstacionamentoPedroApplication {

	public static void main(String[] args) {

		SpringApplication.run(EstacionamentoPedroApplication.class, args);
		MovimentacaoService movimentacao = new MovimentacaoService();
		movimentacao.ControleMovimentacaoEntrada();
	}

}
