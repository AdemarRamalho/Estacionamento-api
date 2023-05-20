package br.com.uniamerica.Estacionamentopedro.repository;

import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import br.com.uniamerica.Estacionamentopedro.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
    @Modifying
    @Query("UPDATE Veiculo veiculo SET veiculo.ativo = false WHERE veiculo.id = :idVeiculo")
    public void desativar(@Param("idVeiculo") Long id);

    @Modifying
    @Query("UPDATE Veiculo veiculo SET veiculo.ativo = true WHERE veiculo.id = :idVeiculo")
    public void ativar(@Param("idVeiculo") Long id);

    @Query("SELECT veiculo FROM Veiculo veiculo WHERE veiculo.ativo = true")
    public List<Veiculo> VeiculosAtivos();


}
