package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.TitreTransport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface TitreTransportRepo extends MongoRepository<TitreTransport, String> {
}
