package org.example.labgticsz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idmesa",nullable = false)
    private int idmesa;

    @Column(name="name",nullable = false)
    @Size(min=3, max=45, message="El campo tiene que estar entre 3 y 45 caracteres.")
    @NotBlank(message = "No puede estar vacío.")
    private String name;
}
