package com.clase10proyecto.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Transient;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ENCUENTRO_PARANORMAL")
public class EncuentroParanormal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String descripcion;

    @NonNull
    @Temporal(TemporalType.DATE)
    private String fecha;

    @NonNull
    @Enumerated(EnumType.STRING)
    private TipoFenomeno tipoFenomeno;

    @OneToMany(mappedBy = "encuentroParanormal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Equipo_Encuentro> equipos = new HashSet<>();

}
