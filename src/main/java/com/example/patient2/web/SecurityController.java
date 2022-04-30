package com.example.patient2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/403")
    public String notAuthorized(){ /*hna ya3ni fach chi utilisateur tente d'accéder à une page dont il n'a pas acces kayredirecta l la page 403*/
        return "403";
    }
}
