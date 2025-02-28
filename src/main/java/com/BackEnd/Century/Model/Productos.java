package com.BackEnd.Century.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", nullable = false)
    private String name;

    @Column(name = "Imagen")
    private String imagen;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Stock", nullable = false)
    private Integer stock; 

    @Column(name = "Precio", nullable = false)
    private Double precio; 

    @Column(name = "Estado", nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedores proveedores;


}
