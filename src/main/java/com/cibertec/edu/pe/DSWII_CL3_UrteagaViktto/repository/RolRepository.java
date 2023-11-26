package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.repository;

import com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends
        JpaRepository<Rol, Integer> {

    Rol findByNomrol(String nombrerol);
}