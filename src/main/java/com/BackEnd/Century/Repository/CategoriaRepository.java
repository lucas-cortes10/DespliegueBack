package com.BackEnd.Century.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BackEnd.Century.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria , Long> {

}
