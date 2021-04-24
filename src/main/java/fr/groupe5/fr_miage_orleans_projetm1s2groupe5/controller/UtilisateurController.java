package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.controller;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.EmailDejaExistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/trans")
public class UtilisateurController {

    @Autowired
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur){
        try {
            this.utilisateurService.inscription(utilisateur);
            return ResponseEntity.created(URI.create("/api/v1/trans/user"+utilisateur.getId())).body("Inscription effectuée avec succès");
        } catch (EmailDejaExistantException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("cet email est deja utilisé");
        }
    }
}
