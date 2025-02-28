package com.BackEnd.Century.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BackEnd.Century.Model.Productos;
import com.BackEnd.Century.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear producto
    public Productos crearProducto(Productos producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Productos> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<Productos> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Actualizar producto
    public Productos actualizarProducto(Long id, Productos producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            return productoRepository.save(producto);
        }
        return null;
    }

    
}
