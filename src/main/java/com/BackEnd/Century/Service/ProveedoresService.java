package com.BackEnd.Century.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BackEnd.Century.Model.Proveedores;
import com.BackEnd.Century.Repository.ProveedorRepository;

@Service
public class ProveedoresService {

    @Autowired
    private ProveedorRepository proveedoresRepository;

    public List<Proveedores> listarProveedores() {
        return proveedoresRepository.findAll();
    }

    public Proveedores crearProveedor(Proveedores proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    public Proveedores obtenerProveedorPorId(Long id) {
        return proveedoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    public Proveedores actualizarProveedor(Long id, Proveedores proveedor) {
        if (!proveedoresRepository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado con ID: " + id);
        }
        return proveedoresRepository.save(proveedor);
    }

    public void eliminarProveedor(Long id) {
        if (!proveedoresRepository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado con ID: " + id);
        }
        proveedoresRepository.deleteById(id);
    }
}
