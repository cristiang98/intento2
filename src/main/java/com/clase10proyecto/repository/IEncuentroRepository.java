package com.clase10proyecto.repository;

import com.clase10proyecto.model.EncuentroParanormal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEncuentroRepository extends JpaRepository<EncuentroParanormal, Long> {
}
