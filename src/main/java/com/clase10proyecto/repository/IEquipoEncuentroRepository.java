package com.clase10proyecto.repository;

import com.clase10proyecto.model.Equipo;
import com.clase10proyecto.model.Equipo_Encuentro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEquipoEncuentroRepository extends CrudRepository<Equipo_Encuentro, Long> {

@Query("SELECT COUNT(ee) FROM Equipo_Encuentro ee WHERE ee.equipo.id = :idEquipo AND ee.encuentroParanormal.id = :idEncuentro")
int countByEquipoAndEncuentro(@Param("idEquipo") Long idEquipo, @Param("idEncuentro") Long idEncuentro);

@Query("SELECT e FROM Equipo_Encuentro ee JOIN ee.equipo e WHERE ee.encuentroParanormal.id = :idEncuentro")
List<Equipo> findEquiposByEncuentro(@Param("idEncuentro") Long idEncuentro);


}
