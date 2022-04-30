package com.example.patient2.web;

import com.example.patient2.entities.Medecin;
import com.example.patient2.entities.Patient;
import com.example.patient2.repositories.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller @AllArgsConstructor
public class MedecinController {

    private MedecinRepository medecinRepository;
    @GetMapping("/admin/medecins")
    public String medecins(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "")String keyword){
        Page<Medecin> medecins=medecinRepository.findByNomContains(keyword, PageRequest.of(page,size));
        /*List<Medecin> medecins=medecinRepository.findAll();*/
        model.addAttribute("pages",new int[medecins.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("listeMedecins",medecins);
        model.addAttribute("keyword",keyword);
        return "medecins";
    }

    @GetMapping("/admin/deletemedecin")
    public String deletemedecin(Long id){
        medecinRepository.deleteById(id);
        return "redirect:/admin/medecins";
    }

    @GetMapping("/admin/nouveaumedecinForm")
    public String nouveaumedecinForm(Model model){
        model.addAttribute("medecin",new Medecin());
        return "nouveaumedecinForm";
    }

    @PostMapping("/admin/savemedecin")
    public String savemedecin(Model model, @Valid Medecin medecin, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "nouveaumedecinForm";
        medecinRepository.save(medecin);
        return "redirect:/admin/medecins";
    }

    @GetMapping("/admin/editmedecin")
    public String editmedecin(Model model,Long id){
        Medecin medecin=medecinRepository.findById(id).orElse(null);
        if(medecin==null) throw new RuntimeException("Médecin introuvable");
        model.addAttribute("medecin",medecin);
        return "editmedecin";
    }
}
