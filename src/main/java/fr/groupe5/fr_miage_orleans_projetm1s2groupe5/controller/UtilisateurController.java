package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.controller;

import com.mongodb.client.model.Collation;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.EmailDejaExistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade.FacadeUtilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade.FacadeUtilisateurImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/trans")
public class UtilisateurController {

    @Autowired
    private final FacadeUtilisateur facadeUtilisateur;

    public UtilisateurController(FacadeUtilisateurImpl facadeUtilisateur) {
        this.facadeUtilisateur = facadeUtilisateur;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur){
        try {
            this.facadeUtilisateur.inscription(utilisateur);
            return ResponseEntity.created(URI.create("/api/v1/trans/user"+utilisateur.getId())).body("Inscription effectuée avec succès");
        } catch (EmailDejaExistantException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("cet email est deja utilisé");
        }
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Utilisateur> recupUtilisateurs(){
        return this.facadeUtilisateur.getAllUtilisateurs();
    }

    @PutMapping(value = "/user/admin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> etreAdmin(@RequestParam String email){
        int i = this.facadeUtilisateur.enableAdmin(email);
        return ResponseEntity.ok().body("Changement effectué avec succès");
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<String> majUtilisateur(@PathVariable("id") Long id, @RequestParam(required = false) String nom, @RequestParam(required = false) String prenom,
                              @RequestParam(required = false) String adresse, @RequestParam(required = false) String telephone){
        try {
            facadeUtilisateur.updateUtilisateur(id, nom, prenom, adresse, telephone);
            return ResponseEntity.ok().body("Modification faite avec succès");
        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("l'utilisateur avec l'id :"+id+" n'existe pas");
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> supprimerUtilisateur(@PathVariable long id){
        try {
            facadeUtilisateur.supprimerUtilisateur(id);
            return ResponseEntity.status(HttpStatus.resolve(204)).body("utilisateur supprimé");
        } catch (UtilisateurInexistantException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
