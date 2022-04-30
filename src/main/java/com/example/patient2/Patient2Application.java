package com.example.patient2;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.patient2.entities.Consultation;
import com.example.patient2.entities.Medecin;
import com.example.patient2.entities.Patient;
import com.example.patient2.repositories.ConsultationRepository;
import com.example.patient2.repositories.MedecinRepository;
import com.example.patient2.repositories.PatientRepository;
import com.example.patient2.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Patient2Application {

    public static void main(String[] args) {

        SpringApplication.run(Patient2Application.class, args);
    }


    @Bean//au démarage elle crée un objet de type passwordEncoder
    PasswordEncoder passwordEncoder(){//return un objet de type passwordEncoder
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"hassan",new Date(),false,182));
            patientRepository.save(new Patient(null,"mohanad",new Date(),true,195));
            patientRepository.save(new Patient(null,"hanae",new Date(),false,186));
            patientRepository.save(new Patient(null,"hassna",new Date(),true,148));
            patientRepository.save(new Patient(null,"hamid",new Date(),false,136));
            patientRepository.findAll().forEach(p ->{
                System.out.println(p.getNom());
            } );
        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("yassmine","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");


            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("mohamed","ADMIN");
            securityService.addRoleToUser("yassmine","USER");
            securityService.addRoleToUser("hassan","USER");

        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(MedecinRepository medecinRepository){
        return args -> {
            medecinRepository.save(new Medecin(null,"Chraibi Hassan","chraibi@gmail.com","Pédiatre"));
            medecinRepository.save(new Medecin(null,"Cherdoudi samah","cherdoudi@gmail.com","Pneumoloque"));
            medecinRepository.save(new Medecin(null,"chakib ilias","chakib@gmail.com","Dentiste"));
            medecinRepository.save(new Medecin(null,"Idrissi imad","idrissi@gmail.com","Cardioloque"));
            medecinRepository.save(new Medecin(null,"Hamid mouhssine","hamid@gmail.com","Neurologue"));

            medecinRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(ConsultationRepository consultationRepository){
        return args -> {
           consultationRepository.save(new Consultation(null,"amine",new Date()));

           consultationRepository.findAll().forEach(p->{
               System.out.println(p.getNom());
           });
        };
    }

}
