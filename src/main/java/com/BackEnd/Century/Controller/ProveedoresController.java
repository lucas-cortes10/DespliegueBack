package com.BackEnd.Century.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BackEnd.Century.Model.Proveedores;
import com.BackEnd.Century.Service.ProveedorPdfService;
import com.BackEnd.Century.Service.ProveedoresService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;

    @Autowired
    private ProveedorPdfService proveedorPdfService;

   
    @GetMapping("/reporte")
    public void descargarReportePDF(HttpServletResponse response) throws IOException {
        //respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=categorias.pdf");

        //generar pdf
        List<Proveedores> proveedores = proveedoresService.listarProveedores();
        proveedorPdfService.generarReporteDeProveedores(response, proveedores);
    }

    @GetMapping("/proveedores")
    public List<Proveedores> listarProveedores() {
        return proveedoresService.listarProveedores();
    }

    @PostMapping("/proveedores")
    public ResponseEntity<Proveedores> guardarProveedor(@RequestBody Proveedores proveedor) {
        Proveedores nuevoProveedor = proveedoresService.crearProveedor(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }

    @GetMapping("/proveedores/{id}")
    public ResponseEntity<Proveedores> obtenerProveedorPorId(@PathVariable Long id) {
        try {
            Proveedores proveedor = proveedoresService.obtenerProveedorPorId(id);
            return ResponseEntity.ok(proveedor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/proveedores/{id}")
    public ResponseEntity<Proveedores> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedores proveedor) {
        try {
            proveedor.setProveedorId(id);
            Proveedores proveedorActualizado = proveedoresService.actualizarProveedor(id, proveedor);
            return ResponseEntity.ok(proveedorActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        try {
            proveedoresService.eliminarProveedor(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
