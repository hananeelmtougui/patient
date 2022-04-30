package com.example.patient2.web;

import com.example.patient2.entities.Consultation;
import com.example.patient2.repositories.ConsultationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor

public class ConsultationController {
@Autowired
    private ConsultationRepository consultationRepository;

    @GetMapping("/user/consultations")
    public String consultations(Model model){
        /*List<Consultation> consultations=consultationRepository.findAll();
        model.addAttribute("listeConsultations",consultations);*/
        return "consultations";
    }

    @GetMapping("/user/nouvelConsultationForm")
    public String nouvelConsultationForm(Model model){
        model.addAttribute("consultation",new Consultation());
        return "nouvelConsultationForm";
    }

    @PostMapping("/user/saveconsultation")
    public String saveconsultation(Model model,Consultation consultation){
        consultationRepository.save(consultation);
        return "nouvelConsultationForm";
    }
}
