package com.clase10proyecto.dto;

import com.clase10proyecto.model.Equipo;
import com.clase10proyecto.model.Miembro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiembroEquipo {

    private Miembro miembro;
    private Equipo equipo;

}
