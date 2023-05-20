package br.com.uniamerica.Estacionamentopedro.repository;

import br.com.uniamerica.Estacionamentopedro.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao,Long> {
}
