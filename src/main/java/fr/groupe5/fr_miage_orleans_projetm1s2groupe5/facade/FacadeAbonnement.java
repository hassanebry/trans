package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.InformationIncorrects;

public interface FacadeAbonnement {
    String souscriptionAbonnement(String username, String type) throws InformationIncorrects;
}
