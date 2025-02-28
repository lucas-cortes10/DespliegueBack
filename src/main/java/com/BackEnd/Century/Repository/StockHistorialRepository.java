package com.BackEnd.Century.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BackEnd.Century.Model.StockHistorial;

@Repository
public interface StockHistorialRepository extends JpaRepository<StockHistorial, Long> {
    List<StockHistorial> findByProductoId(Long productoId);
    List<StockHistorial> findByAdministradorId(String administradorId);
}