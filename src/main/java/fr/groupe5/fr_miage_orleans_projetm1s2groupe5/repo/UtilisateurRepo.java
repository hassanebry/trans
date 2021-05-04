package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE Utilisateur u " + "SET u.admin = TRUE WHERE u.email = ?1")
    int enableAdmin(String email);


    Optional<Utilisateur> findByUsername(String username);
}
