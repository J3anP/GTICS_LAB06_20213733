package org.example.labgticsz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iduser",nullable = false)
    private int iduser;

    @Column(name="name",nullable = false)
    @Size(min=3, max=45, message="El campo tiene que estar entre 3 y 45 caracteres.")
    @NotBlank(message = "No puede estar vacío.")
    private String name;

    @Column(name="lastname",nullable = false)
    @Size(min=3, max=45, message="El campo tiene que estar entre 3 y 45 caracteres.")
    @NotBlank(message = "No puede estar vacío.")
    private String lastname;

    @Column(name="password",nullable = false)
    @Size(min=1,max=45,message="Debe tener menos de 45 caracteres")
    private String password;

    @Column(name="email",nullable = false)
    @Size(min=1,max=45,message="Debe tener menos de 45 caracteres")
    private String email;

    @ManyToOne
    @JoinColumn(name="idrol")
    private Rol rol;
}
