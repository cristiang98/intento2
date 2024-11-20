package com.clase10proyecto.controller;

import com.clase10proyecto.model.Caso;
import com.clase10proyecto.model.Equipo;
import com.clase10proyecto.model.Miembro;
import com.clase10proyecto.repository.ICasoRepository;
import com.clase10proyecto.repository.IEquipoRepository;
import com.clase10proyecto.repository.IMiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/miembros")
public class MiembroController {

    @Autowired
    private IMiembroRepository miembroRepository;

    @Autowired
    private IEquipoRepository equipoRepository;

    @Autowired
    private ICasoRepository casoRepository;

    // controller para ver miembros
    @GetMapping("")
    public String getMiembros(Model model) {
        model.addAttribute("miembros", miembroRepository.findAll());
        return "miembros";
    }

    //controller para ver equipo
    @GetMapping("/ver-equipo/{id}")
    public String verEquipo(@PathVariable("id") Long id, Model model) {
        Miembro miembro = miembroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        Equipo equipo = equipoRepository.findById(id).orElseThrow();

        model.addAttribute("miembro", miembro);
        model.addAttribute("equipo", equipo);

        return "verEquipo";
    }

    @GetMapping("/crear-caso/{id}")
    public String crearCaso(@PathVariable("id") Long id, Model model) {
        Miembro miembro = miembroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("miembro", miembro);
        model.addAttribute("caso", new Caso());
        System.out.println(casoRepository.findAll());
        return "agregarCaso";
    }

    @PostMapping("/crear-caso/{id}")
    public String crearCaso(@PathVariable("id") Long id, @ModelAttribute Caso caso, Model model) {
        // Encontrar al miembro por ID
        Miembro miembro = miembroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        // Asignar el miembro al caso antes de guardar
        caso.setId(null);
        caso.setMiembro(miembro);
        // Guardar el caso
        casoRepository.save(caso);

        model.addAttribute("caso", caso);
        return "redirect:/miembros"; // Redirigir a la lista de miembros
    }

//        buscar casos por id
    @GetMapping("/byId/{id}")
    public String getCasos(@PathVariable("id") Long id, Model model) {
        // Encontrar al miembro por ID
        Miembro miembro = miembroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        // Encontrar los casos por ID de miembro
        Set<Caso> casos = casoRepository.findByMiembroId(id);

        // Agregar los atributos al modelo
        model.addAttribute("miembro", miembro);
        model.addAttribute("casos", casos);
        return "casosById";
    }

}
