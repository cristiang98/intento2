package com.clase10proyecto.controller;

import com.clase10proyecto.model.EncuentroParanormal;
import com.clase10proyecto.model.Equipo;
import com.clase10proyecto.model.Equipo_Encuentro;
import com.clase10proyecto.repository.IEncuentroRepository;
import com.clase10proyecto.repository.IEquipoEncuentroRepository;
import com.clase10proyecto.repository.IEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/encuentro")
public class EncuentroController {

    @Autowired
    private IEncuentroRepository encuentroRepository;

    @Autowired
    private IEquipoRepository equipoRepository;

    @Autowired
    private IEquipoEncuentroRepository equipoEncuentroRepository;

    @GetMapping("")
    public String getEncuentros(Model model){
        model.addAttribute("encuentros", encuentroRepository.findAll());
        return "encuentros";
    }

    // controller para ver plantilla de crear encuentro paranormal
    @GetMapping("/crear")
    public String mostrarFormularioEncuentro(Model model){

        EncuentroParanormal encuentroParanormal = new EncuentroParanormal();
        model.addAttribute("encuentroParanormal", encuentroParanormal);
        return "agregarEncuentros";
    }

    @PostMapping("/crear")
    public String crearEncuentro(@ModelAttribute EncuentroParanormal encuentroParanormal, Model model){

        encuentroRepository.save(encuentroParanormal);
        model.addAttribute("encuentroParanormal", encuentroParanormal);

        return "redirect:/encuentro";
    }

    // controller para ver plantilla de asignar equipos a un encuentro
    // pendiente

    @GetMapping("/asignar-equipos/{id}")
    public String mostrarFormularioAsignarEquipos(@PathVariable("id") Long id, Model model) {
        EncuentroParanormal encuentro = encuentroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid encuentro Id:" + id));
        model.addAttribute("encuentro", encuentro);
        model.addAttribute("equipos", equipoRepository.findAll());
        return "agregarEquipoEncuentros";
    }

@PostMapping("/asignar-equipos/{id}")
public String asignarEquipos(@PathVariable("id") Long id, @RequestParam("equipoIds") Set<Long> equipoIds, Model model) {
    EncuentroParanormal encuentro = encuentroRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid encuentro Id:" + id));

    // Verificar si ya existen equipos asignados al encuentro
    int count = equipoEncuentroRepository.countByEquipoAndEncuentro(id, id);

    if (count == 0) {
        equipoIds.forEach(equipoId -> {
            Equipo equipo = equipoRepository.findById(equipoId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid equipo Id:" + equipoId));
            Equipo_Encuentro equipoEncuentro = Equipo_Encuentro.builder()
                    .equipo(equipo)
                    .encuentroParanormal(encuentro)
                    .build();
            equipoEncuentroRepository.save(equipoEncuentro);
        });
    } else {
        System.out.println("Ya se asignaron los equipos");
    }
    return "redirect:/encuentro";
}



    // controller para ver equipos por encuentro
    @GetMapping("equiposByEncuentros/{id}")
    public String getEquiposByEncuentros(@PathVariable("id") Long idEncuentro, Model model){
        EncuentroParanormal encuentroParanormal = encuentroRepository.findById(idEncuentro).orElseThrow(() -> new IllegalArgumentException("Invalid encuentro Id:" + idEncuentro));
        model.addAttribute("encuentro", encuentroParanormal);
        model.addAttribute("equipos", equipoEncuentroRepository.findEquiposByEncuentro(idEncuentro));
        return "equiposByEncuentros";
    }

}
