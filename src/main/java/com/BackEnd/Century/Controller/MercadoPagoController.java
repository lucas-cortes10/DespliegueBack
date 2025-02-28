package com.BackEnd.Century.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BackEnd.Century.Service.MercadoPagoService;

@RestController
@RequestMapping("/api/v1/mercadopago")
@CrossOrigin(origins = "http://localhost:3000")  
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;  

    @PostMapping("/crear-preferencia")
    public ResponseEntity<Map<String, Object>> crearPreferencia(@RequestBody Map<String, Object> datos) {
    try {
    
        Map<String, Object> respuesta = mercadoPagoService.crearPreferencia(datos);
        
        
        respuesta.put("correo", datos.get("correo"));
        
        return ResponseEntity.ok(respuesta);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Error al crear la preferencia: " + e.getMessage()));
    }
    }
}
