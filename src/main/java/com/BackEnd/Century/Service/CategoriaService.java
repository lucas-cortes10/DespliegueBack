package com.BackEnd.Century.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BackEnd.Century.Model.Categoria;
import com.BackEnd.Century.Repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todas las categorías
    public List<Categoria> listaCategoria() {
        return categoriaRepository.findAll();
    }

    // Obtener una categoría por ID
    public Categoria obtenerCategoriaId(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("La categoría con ese ID no existe: " + categoriaId));
    }

    // Crear una nueva categoría
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Actualizar una categoría 
    public Categoria updateCategoria(Long categoriaId, Categoria categoriaRequest) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("La categoría con ese ID no existe: " + categoriaId));

        categoria.setName(categoriaRequest.getName());
        return categoriaRepository.save(categoria);
    }

    // Eliminar una categoría por ID
    public void eliminarCategoria(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("La categoría con ese ID no existe: " + categoriaId));

        categoriaRepository.delete(categoria);
    }
}
