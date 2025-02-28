package com.BackEnd.Century.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MercadoPagoService {

    private static final String MERCADOPAGO_API_URL = "https://api.mercadopago.com/checkout/preferences";
    private static final String ACCESS_TOKEN = "APP_USR-6122100356916685-022521-3c4cc3bab22905f0df32af5bd22c3bcf-510277497"; 

    public Map<String, Object> crearPreferencia(Map<String, Object> datos) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(ACCESS_TOKEN);
        
            // Extraer los datos
            Double total = Double.valueOf(datos.get("total").toString());
            String correo = (String) datos.get("correo");

        
           

            Map<String, Object> preferencia = Map.of(
            "items", List.of(Map.of(
                "title", "Compra en Century",
                "description", "Compra realizada por: " + correo,  
                "quantity", 1,
                "currency_id", "COP",
                "unit_price", total
            )),
            "payer", Map.of( 
                "email", correo  
            ),
            "back_urls", Map.of(
                "success", "http://localhost:3000/success",
                "failure", "http://localhost:3000/usuario/errorPago",
                "pending", "http://localhost:3000/pending"
            ),  
            "auto_return", "approved"
        );


            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(preferencia, headers);
            ResponseEntity<Map> response = restTemplate.exchange(
                MERCADOPAGO_API_URL, HttpMethod.POST, requestEntity, Map.class
            );

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Error al crear la preferencia: " + e.getMessage());
        }
    }
}
