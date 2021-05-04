package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.InformationIncorrects;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;


public interface FacadeTitreTransport {

    String commanderTitreTransport(String username, String type) throws UtilisateurInexistantException, InformationIncorrects;
    String validerTicket(String username,  String id) throws InformationIncorrects;
}
