package org.example.labgticsz.controller;

import jakarta.validation.Valid;
import org.example.labgticsz.entity.Mesa;
import org.example.labgticsz.repository.MesaRepository;
import org.example.labgticsz.repository.ReservaRepository;
import org.example.labgticsz.repository.RolRepository;
import org.example.labgticsz.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype   .Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class HomeController {
    final MesaRepository mesaRepository;
    final ReservaRepository reservaRepository;
    final RolRepository rolRepository;
    final UserRepository userRepository;

    public HomeController(MesaRepository mesaRepository, ReservaRepository reservaRepository, RolRepository rolRepository, UserRepository userRepository) {
        this.mesaRepository = mesaRepository;
        this.reservaRepository = reservaRepository;
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value="/list")
    public String list(Model model, @RequestParam(name = "search", required = false) String search) {

        if(search == null){
            model.addAttribute("listaMesas", mesaRepository.findAll());
        }else{
            model.addAttribute("listaMesas", mesaRepository.findByNameIgnoreCaseContaining(search));
            model.addAttribute("search",search);
        }
        return "list";
    }

    @PostMapping("/save")
    public String guardarPersonaje(@ModelAttribute("mesa") @Valid Mesa mesa, BindingResult bindingResult,
                                   RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            return "frmMesa";
        } else {
            if (mesa.getIdmesa() == 0) {
                attr.addFlashAttribute("msg", "Mesa agregada exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Mesa actualizado exitosamente");
            }
            mesaRepository.save(mesa);
            return "redirect:/home/list";
        }
    }
}
