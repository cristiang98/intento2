package com.clase10proyecto.controller;

import com.clase10proyecto.dto.MiembroEquipo;
import com.clase10proyecto.model.Caso;
import com.clase10proyecto.model.Equipo;
import com.clase10proyecto.model.Miembro;
import com.clase10proyecto.repository.ICasoRepository;
import com.clase10proyecto.repository.IEquipoRepository;
import com.clase10proyecto.repository.IMiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/miembros-equipos")
public class MiembroEquipoController {

    @Autowired
    private IMiembroRepository miembroRepository;

    @Autowired
    private IEquipoRepository equipoRepository;

    @Autowired
    private ICasoRepository casoRepository;


    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        MiembroEquipo miembroYEquipo = new MiembroEquipo();
        model.addAttribute("miembroYEquipo", miembroYEquipo);
        return "crearMiembroEquipo";  // ruta del archivo HTML
    }

    @Transactional
    @PostMapping("/crear")
    public String crearMiembroYEquipo(@ModelAttribute MiembroEquipo miembroYEquipo, Model model) {
        try {
            // Guardar el Miembro
            Miembro miembro = miembroYEquipo.getMiembro();

            // Asignar el id del Miembro al Equipo
            Equipo equipo = miembroYEquipo.getEquipo();
            equipo.setMiembro(miembro);

            // Guardar el Equipo y el Miembro
            miembroRepository.save(miembro);
            equipoRepository.save(equipo);

            model.addAttribute("miembro", miembro);
            model.addAttribute("equipo", equipo);
            return "redirect:/miembros"; // Redirigir o mostrar la lista de miembros
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "El nombre del equipo ya existe. Por favor, elige otro nombre.");
            return "crearMiembroEquipo"; // Volver al formulario de creación
        }
    }



}
