package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Abonnement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbonnementRepo extends MongoRepository<Abonnement, String> {
}
