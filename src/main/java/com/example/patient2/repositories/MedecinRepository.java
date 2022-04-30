package com.example.patient2.repositories;

import com.example.patient2.entities.Medecin;
import com.example.patient2.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Page<Medecin> findByNomContains(String kw, Pageable pageable);
}
