package com.BackEnd.Century.Controller;

import com.BackEnd.Century.Model.StockHistorial;
import com.BackEnd.Century.Repository.StockHistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stockhistorial")
@CrossOrigin(origins = "*")
public class StockHistorialController {
    
    @Autowired
    private StockHistorialRepository stockHistorialRepository;
    
    @GetMapping
    public List<StockHistorial> getAllStockHistorial() {
        return stockHistorialRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StockHistorial> getStockHistorialById(@PathVariable Long id) {
        return stockHistorialRepository.findById(id)
                .map(historial -> new ResponseEntity<>(historial, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/producto/{productoId}")
    public List<StockHistorial> getHistorialByProducto(@PathVariable Long productoId) {
        return stockHistorialRepository.findByProductoId(productoId);
    }
    
    @GetMapping("/administrador/{administradorId}")
    public List<StockHistorial> getHistorialByAdministrador(@PathVariable String administradorId) {
        return stockHistorialRepository.findByAdministradorId(administradorId);
    }
    
    @PostMapping
    public ResponseEntity<StockHistorial> createStockHistorial(@RequestBody Map<String, Object> payload) {
        try {
            StockHistorial stockHistorial = new StockHistorial();
            
            stockHistorial.setProductoId(Long.valueOf(payload.get("productoId").toString()));
            stockHistorial.setProductoNombre((String) payload.get("productoNombre"));
            stockHistorial.setStockAnterior(Integer.valueOf(payload.get("stockAnterior").toString()));
            stockHistorial.setStockNuevo(Integer.valueOf(payload.get("stockNuevo").toString()));
            stockHistorial.setAdministradorId((String) payload.get("administradorId"));
            stockHistorial.setAdministradorCorreo((String) payload.get("administradorCorreo"));
            
            // Parse date if provided, otherwise use current time
            if (payload.containsKey("fechaCambio")) {
                stockHistorial.setFechaCambio(LocalDateTime.parse((String) payload.get("fechaCambio")));
            } else {
                stockHistorial.setFechaCambio(LocalDateTime.now());
            }
            
            StockHistorial saved = stockHistorialRepository.save(stockHistorial);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}