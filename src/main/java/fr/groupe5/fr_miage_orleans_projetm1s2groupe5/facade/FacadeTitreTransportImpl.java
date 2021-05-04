package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.InformationIncorrects;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.TitreTransport;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.TitreTransportRepo;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component("facadeTitreTransport")
public class FacadeTitreTransportImpl implements FacadeTitreTransport{

    @Autowired
    private final UtilisateurRepo utilisateurRepo;

    @Autowired
    private final TitreTransportRepo titreTransportRepo;

    public FacadeTitreTransportImpl(UtilisateurRepo utilisateurRepo, TitreTransportRepo titreTransportRepo) {
        this.utilisateurRepo = utilisateurRepo;
        this.titreTransportRepo = titreTransportRepo;
    }

    @Override
    public String commanderTitreTransport(String username, String type) throws UtilisateurInexistantException, InformationIncorrects {
        Optional<Utilisateur> user = utilisateurRepo.findByUsername(username);
        String idTitre = "";
        if (user.isEmpty()){
            throw new UtilisateurInexistantException();
        }
        LocalDateTime expiration = LocalDateTime.now();
        if (type.equals("1H")){
            TitreTransport monTitre = new TitreTransport(type, expiration.plusMinutes(60));
            titreTransportRepo.save(monTitre);
        }else if (type.equals("24H")){
            TitreTransport monTitre = new TitreTransport(type, expiration.plusHours(24));
            idTitre = monTitre.getId();
            titreTransportRepo.save(monTitre);
        }else {
            throw new InformationIncorrects();
        }

        return idTitre;
    }

    @Override
    @Transactional
    public String validerTicket(String username, String id) throws InformationIncorrects {
        Optional<TitreTransport> monTitre = titreTransportRepo.findById(id);
        if (monTitre.isEmpty()){
            throw new InformationIncorrects();
        }
        if (monTitre.get().getType().equals("24H")){
            monTitre.get().setDateExpiration(LocalDateTime.now().plusHours(24));
            monTitre.get().setValid(true);
        }else if (monTitre.get().getType().equals("1H")){
            monTitre.get().setDateExpiration(LocalDateTime.now().plusMinutes(60));
            monTitre.get().setValid(true);
        }

        return username;
    }
}
