package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.repository;

import com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.model.bd.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends
        JpaRepository<Usuario, Integer> {

    Usuario findByNomusuario(String nomusuario);

}
