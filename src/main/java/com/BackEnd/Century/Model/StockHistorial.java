package com.BackEnd.Century.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_historial")
public class StockHistorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long productoId;
    private String productoNombre;
    private Integer stockAnterior;
    private Integer stockNuevo;
    private String administradorId;
    private String administradorCorreo;
    private LocalDateTime fechaCambio;
    
    // Constructors
    public StockHistorial() {
    }
    
    public StockHistorial(Long productoId, String productoNombre, Integer stockAnterior, 
                          Integer stockNuevo, String administradorId, String administradorCorreo, 
                          LocalDateTime fechaCambio) {
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.administradorId = administradorId;
        this.administradorCorreo = administradorCorreo;
        this.fechaCambio = fechaCambio;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getProductoId() {
        return productoId;
    }
    
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
    
    public String getProductoNombre() {
        return productoNombre;
    }
    
    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }
    
    public Integer getStockAnterior() {
        return stockAnterior;
    }
    
    public void setStockAnterior(Integer stockAnterior) {
        this.stockAnterior = stockAnterior;
    }
    
    public Integer getStockNuevo() {
        return stockNuevo;
    }
    
    public void setStockNuevo(Integer stockNuevo) {
        this.stockNuevo = stockNuevo;
    }
    
    public String getAdministradorId() {
        return administradorId;
    }
    
    public void setAdministradorId(String administradorId) {
        this.administradorId = administradorId;
    }
    
    public String getAdministradorCorreo() {
        return administradorCorreo;
    }
    
    public void setAdministradorCorreo(String administradorCorreo) {
        this.administradorCorreo = administradorCorreo;
    }
    
    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }
    
    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
}