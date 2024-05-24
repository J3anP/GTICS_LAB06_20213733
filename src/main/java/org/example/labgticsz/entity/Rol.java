package org.example.labgticsz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrol",nullable = false)
    private int idmesa;

    @Column(name="name",nullable = false)
    @Size(min=3, max=45, message="El campo tiene que estar entre 3 y 45 caracteres.")
    @NotBlank(message = "No puede estar vacío.")
    private String name;
}
