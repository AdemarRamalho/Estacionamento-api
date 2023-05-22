package Estacionamentopedro.Repository;

import Estacionamentopedro.Entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Long> {

    @Modifying
    @Query("UPDATE Modelo modelo SET modelo.ativo = false WHERE modelo.id = :idModelo")
    public void desativar(@Param("idModelo") Long id);

    @Modifying
    @Query("UPDATE Modelo modelo SET modelo.ativo = true WHERE modelo.id = :idModelo")
    public void ativar(@Param("idModelo") Long id);

    @Query("SELECT Modelo FROM Modelo modelo WHERE modelo.ativo = true")
    public List<Modelo> modelosAtivos();


}
