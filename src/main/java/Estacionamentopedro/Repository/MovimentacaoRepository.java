package Estacionamentopedro.Repository;

import Estacionamentopedro.Entity.Configuracao;
import Estacionamentopedro.Entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Long> {
    @Modifying
    @Query("UPDATE Movimentacao movimentacao SET movimentacao.ativo = false WHERE movimentacao.id = :idMovimentacao")
    public void desativar(@Param("idMovimentacao") Long id);

    @Modifying
    @Query("UPDATE Movimentacao movimentacao SET movimentacao.ativo = true WHERE movimentacao.id = :idMovimentacao")
    public void ativar(@Param("idMovimentacao") Long id);

    @Query("SELECT movimentacao FROM Movimentacao movimentacao WHERE movimentacao.ativo = true")
    public List<Movimentacao> MovimentacoesAtivas();

    @Query("SELECT configuracao FROM Configuracao configuracao")
    Configuracao buscaConfig();
}