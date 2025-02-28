package com.BackEnd.Century.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BackEnd.Century.Model.Proveedores;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedores, Long> {

}
