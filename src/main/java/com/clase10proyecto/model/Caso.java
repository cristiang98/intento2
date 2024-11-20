package com.clase10proyecto.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CASO")
public class Caso {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String ubicacion;

    @NonNull
    @Enumerated(EnumType.STRING)
    private NivelActividad nivelActividad;

    @NonNull
    @Temporal(TemporalType.DATE)
    private String fechaApertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id", nullable = false)
    private Miembro miembro; // viene de la clase Miembro
}
