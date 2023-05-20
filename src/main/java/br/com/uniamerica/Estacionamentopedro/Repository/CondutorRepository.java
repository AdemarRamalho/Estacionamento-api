package br.com.uniamerica.Estacionamentopedro.repository;

import br.com.uniamerica.Estacionamentopedro.entity.Condutor;
import br.com.uniamerica.Estacionamentopedro.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor,Long> {

    @Modifying
    @Query("UPDATE Condutor condutor SET condutor.ativo = false WHERE condutor.id = :idCondutor")
    public void desativar(@Param("idCondutor") Long id);

    @Modifying
    @Query("UPDATE Condutor condutor SET condutor.ativo = true WHERE condutor.id = :idCondutor")
    public void ativar(@Param("idCondutor") Long id);

    @Query("SELECT Condutor FROM Condutor condutor WHERE condutor.ativo = true")
    public List<Condutor> CondutoresAtivos();
}
