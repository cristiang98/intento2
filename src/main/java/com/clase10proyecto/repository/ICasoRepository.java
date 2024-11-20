package com.clase10proyecto.repository;

import com.clase10proyecto.model.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ICasoRepository extends JpaRepository<Caso, Long> {
    @Query("SELECT c FROM Caso c WHERE c.miembro.id = :miembroId")
    Set<Caso> findByMiembroId(@Param("miembroId") Long miembroId);



    @Query("SELECT c FROM Caso c WHERE c.miembro.id = :idMiembro")
    Set<Caso> findCasosByMiembroId(@Param("idMiembro") Long idMiembro);
}
