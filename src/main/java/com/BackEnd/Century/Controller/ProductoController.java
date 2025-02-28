package com.BackEnd.Century.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BackEnd.Century.Model.Productos;
import com.BackEnd.Century.Service.ProductoPdfService;
import com.BackEnd.Century.Service.ProductoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoPdfService productoPdfService;

    @GetMapping("/reporte")
    public void descargarReportePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=categorias.pdf");

        List<Productos> productos = productoService.obtenerTodosLosProductos();
        productoPdfService.generarReporteDeProveedores(response, productos);
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<Productos> crearProducto(@RequestBody Productos producto) {
        // Establecer el estado como true por defecto
        producto.setEstado(true);
        Productos productoCreado = productoService.crearProducto(producto);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Productos>> obtenerTodosLosProductos() {
        List<Productos> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Productos> producto = productoService.obtenerProductoPorId(id);
        if (producto.isPresent()) {
            return new ResponseEntity<>(producto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Long id, @RequestBody Productos producto) {
        Productos productoActualizado = productoService.actualizarProducto(id, producto);
        if (productoActualizado != null) {
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Cambiar estado del producto
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Productos> cambiarEstadoProducto(@PathVariable Long id) {
        Optional<Productos> productoOpt = productoService.obtenerProductoPorId(id);
        if (productoOpt.isPresent()) {
            Productos producto = productoOpt.get();
            producto.setEstado(!producto.getEstado()); // Cambiar el estado actual
            Productos productoActualizado = productoService.actualizarProducto(id, producto);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Ya no se usa eliminar físicamente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        Optional<Productos> productoOpt = productoService.obtenerProductoPorId(id);
        if (productoOpt.isPresent()) {
            Productos producto = productoOpt.get();
            producto.setEstado(false); // En lugar de eliminar, desactivamos
            productoService.actualizarProducto(id, producto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}