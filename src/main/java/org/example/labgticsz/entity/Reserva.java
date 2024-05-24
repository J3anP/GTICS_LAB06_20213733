package org.example.labgticsz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idprestamos",nullable = false)
    private int idreserva;

    @Column(name = "reservationdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaReserva;

    @Column(name = "reservationdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRetorno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User", referencedColumnName = "iduser")
    private User cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Mesa", referencedColumnName = "idmesa")
    private Mesa mesa;

}
