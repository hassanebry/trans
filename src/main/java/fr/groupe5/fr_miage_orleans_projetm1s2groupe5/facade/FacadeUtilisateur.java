package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.EmailDejaExistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;

import java.util.Collection;

public interface FacadeUtilisateur {

    Utilisateur inscription(Utilisateur utilisateur) throws EmailDejaExistantException;
    Collection<Utilisateur> getAllUtilisateurs();
    int enableAdmin(String email);


    void updateUtilisateur(Long id, String nom, String prenom, String adresse, String telephone) throws UtilisateurInexistantException;

    void supprimerUtilisateur(long id) throws UtilisateurInexistantException;
}
