package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.AucunTitreAcheteeException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.InformationIncorrects;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.TitreDejaValidException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.TitreTransport;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.TitreTransportRepo;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

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
            TitreTransport monTitre = new TitreTransport(username, type, expiration.plusMinutes(60));
            titreTransportRepo.save(monTitre);
        }else if (type.equals("24H")){
            TitreTransport monTitre = new TitreTransport(username, type, expiration.plusHours(24));
            idTitre = monTitre.getId();
            titreTransportRepo.save(monTitre);
        }else {
            throw new InformationIncorrects();
        }

        return idTitre;
    }

    @Override
    @Transactional
    public String validerTicket(String username, String id) throws InformationIncorrects, TitreDejaValidException {
        Optional<TitreTransport> monTitre = titreTransportRepo.findById(id);
        if (monTitre.isEmpty()){
            throw new InformationIncorrects();
        }
        if (monTitre.get().isValid()){
            throw new TitreDejaValidException();
        }
        if (monTitre.get().getType().equals("24H")){
            monTitre.get().setDateExpiration(LocalDateTime.now().plusHours(24));
            monTitre.get().setValid(true);
            titreTransportRepo.save(monTitre.get());
        }else if (monTitre.get().getType().equals("1H")){
            monTitre.get().setDateExpiration(LocalDateTime.now().plusMinutes(60));
            monTitre.get().setValid(true);
            titreTransportRepo.save(monTitre.get());
        }

        return username;
    }

    @Override
    public Collection<TitreTransport> titreSejourParUtilisateur(String username) throws UtilisateurInexistantException, AucunTitreAcheteeException {
        Optional<Utilisateur> user = utilisateurRepo.findByUsername(username);
        Collection<TitreTransport> mesTitres = titreTransportRepo.findTitreTransportByUsername(username);
        if (user.isEmpty()){
            throw new UtilisateurInexistantException();
        }
        if (mesTitres.isEmpty()){
            throw new AucunTitreAcheteeException();
        }

        return mesTitres;
    }
}
