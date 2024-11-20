package com.clase10proyecto.controller;

import com.clase10proyecto.model.Equipo;
import com.clase10proyecto.model.Miembro;
import com.clase10proyecto.repository.IEquipoRepository;
import com.clase10proyecto.repository.IMiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/equipos")
public class EquipoController {


    @Autowired
    private IEquipoRepository equipoRepository;

    @GetMapping("")
    public String getEquipo(Model model) {
        model.addAttribute("prueba", equipoRepository.findAll());
        return "equipos";
    }

    // controller para ver ver cuentros por equipo
    @GetMapping("/encuentroByEquipo/{id}")
    public String getEncuentroByEquipo(@PathVariable("id") Long id, Model model) {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid equipo Id:" + id));
        model.addAttribute("equipo", equipo);
        model.addAttribute("encuentros", equipoRepository.findEncuentrosByEquipoId(id));
        return "encuentrosByEquipo";
    }
}
