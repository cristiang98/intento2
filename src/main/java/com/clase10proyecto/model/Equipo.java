package com.clase10proyecto.model;

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
@Table(name = "EQUIPO", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @Column(name = "nombre", unique = true)
    private String nombre;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "estado_funcional")
    @NonNull
    private boolean estadoFuncional;

    @OneToOne
    @JoinColumn(name = "id_miembro", unique = true)
    private Miembro miembro;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Equipo_Encuentro> equipos = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(id, equipo.id) && Objects.equals(nombre, equipo.nombre);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
