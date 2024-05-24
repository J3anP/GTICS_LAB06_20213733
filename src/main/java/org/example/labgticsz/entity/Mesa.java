package org.example.labgticsz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="mesas")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idmesa",nullable = false)
    private int idmesa;

    @Column(name="name",nullable = false)
    @Size(min=3, max=45, message="El campo tiene que estar entre 3 y 45 caracteres.")
    @NotBlank(message = "No puede estar vacío.")
    private String name;

    private int capacity;

    @Column(name="ubicacion")
    @Size(min=3, max=45, message="El campo tiene que estar entre 3 y 45 caracteres.")
    private String ubicacion;

}
