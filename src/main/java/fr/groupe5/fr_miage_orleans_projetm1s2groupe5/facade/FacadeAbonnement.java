package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.InformationIncorrects;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Abonnement;

import java.util.Collection;

public interface FacadeAbonnement {
    String souscriptionAbonnement(String username, String type) throws InformationIncorrects, UtilisateurInexistantException;
    Collection<Abonnement> abonnementParUser(String username) throws UtilisateurInexistantException;
}
