package Estacionamentopedro.Repository;

import Estacionamentopedro.Entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {

    @Modifying
    @Query("UPDATE Marca marca SET marca.ativo = false WHERE marca.id = :idMarca")
    public void desativar(@Param("idMarca") Long id);

    @Modifying
    @Query("UPDATE Marca marca SET marca.ativo = true WHERE marca.id = :idMarca")
    public void ativar(@Param("idMarca") Long id);

    @Query("SELECT marca FROM Marca marca WHERE marca.ativo = true")
    public List<Marca> MarcasAtivas();

}