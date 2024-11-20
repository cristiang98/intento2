package com.clase10proyecto.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Transient;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MIEMBRO")
public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    private String nombre;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Rol rol; // Asumiendo que tienes un Enum llamado Rol
    @NonNull
    private Integer edad;
    @NonNull
    private String especialidad;

    @OneToOne(mappedBy = "miembro" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Equipo equipo;

    @OneToMany(mappedBy = "miembro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Caso> casos = new HashSet<>();


    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miembro miembro = (Miembro) o;
        return Objects.equals(id, miembro.id) && Objects.equals(nombre, miembro.nombre);
    }

    @Override
    public String toString() {
        return "Miembro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", equipoId=" + (equipo != null ? equipo.getId() : "null") +
                '}';
    }
}

