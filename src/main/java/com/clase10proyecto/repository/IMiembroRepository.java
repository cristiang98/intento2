package com.clase10proyecto.repository;
import com.clase10proyecto.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IMiembroRepository extends JpaRepository<Miembro, Long> {


}
