package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;

import com.mongodb.client.MongoClients;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.InformationIncorrects;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Abonnement;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.AbonnementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component("facadeAbonnement")
public class FacadeAbonnementImpl implements FacadeAbonnement{

    @Autowired
    private final AbonnementRepo abonnementRepo;

    public FacadeAbonnementImpl(AbonnementRepo abonnementRepo) {
        this.abonnementRepo = abonnementRepo;
    }

    @Override
    public String souscriptionAbonnement(String username, String type) throws InformationIncorrects {
        LocalDateTime dateSouscription = LocalDateTime.now();
        LocalDateTime dateExpiration;

        if (type.equals("mensuel")){
            dateExpiration = dateSouscription.plusDays(30);
            Abonnement abonnementMensuel = new Abonnement(type, dateSouscription, dateExpiration);
            abonnementRepo.save(abonnementMensuel);
        }else if (type.equals("annuel")){
            dateExpiration = dateSouscription.plusMonths(12);
            Abonnement abonnementAnnuel = new Abonnement(type, dateSouscription, dateExpiration);
            abonnementRepo.save(abonnementAnnuel);
        }else {
            throw new InformationIncorrects();
        }

        return username;
    }
}
