package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.InformationIncorrects;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Abonnement;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.AbonnementRepo;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Component("facadeAbonnement")
public class FacadeAbonnementImpl implements FacadeAbonnement{

    @Autowired
    private final AbonnementRepo abonnementRepo;

    @Autowired
    private final UtilisateurRepo utilisateurRepo;

    public FacadeAbonnementImpl(AbonnementRepo abonnementRepo, UtilisateurRepo utilisateurRepo) {

        this.abonnementRepo = abonnementRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public String souscriptionAbonnement(String username, String type) throws InformationIncorrects, UtilisateurInexistantException {
        Optional<Utilisateur> user = utilisateurRepo.findByUsername(username);
        if (user.isEmpty()){
            throw new UtilisateurInexistantException();
        }
        LocalDateTime dateSouscription = LocalDateTime.now();
        LocalDateTime dateExpiration;

        if (type.toLowerCase(Locale.ROOT).equals("mensuel")){
            dateExpiration = dateSouscription.plusDays(30);
            Abonnement abonnementMensuel = new Abonnement(username, type, dateSouscription, dateExpiration);
            abonnementRepo.save(abonnementMensuel);
        }else if (type.toLowerCase(Locale.ROOT).equals("annuel")){
            dateExpiration = dateSouscription.plusMonths(12);
            Abonnement abonnementAnnuel = new Abonnement(username, type, dateSouscription, dateExpiration);
            abonnementRepo.save(abonnementAnnuel);
        }else {
            throw new InformationIncorrects();
        }

        return username;
    }

    @Override
    public Collection<Abonnement> abonnementParUser(String username) throws UtilisateurInexistantException {
        Optional<Utilisateur> user = utilisateurRepo.findByUsername(username);
        List<Abonnement> abonnementsByUsername = abonnementRepo.findAbonnementsByUsername(username);
        if (user.isEmpty()){
            throw new UtilisateurInexistantException();
        }

        return abonnementsByUsername;
    }
}
