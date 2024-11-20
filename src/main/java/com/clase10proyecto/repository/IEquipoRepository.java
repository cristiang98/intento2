package com.clase10proyecto.repository;

import com.clase10proyecto.model.EncuentroParanormal;
import com.clase10proyecto.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IEquipoRepository extends JpaRepository<Equipo, Long> {
    @Query("SELECT e FROM EncuentroParanormal e WHERE e.id IN " +
            "(SELECT ee.encuentroParanormal.id FROM Equipo_Encuentro ee WHERE ee.equipo.id = :idEquipo)")
    List<EncuentroParanormal> findEncuentrosByEquipoId(@Param("idEquipo") Long idEquipo);

}
