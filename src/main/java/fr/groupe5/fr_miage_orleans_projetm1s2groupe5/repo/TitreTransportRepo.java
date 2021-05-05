package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo;

import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.TitreTransport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TitreTransportRepo extends MongoRepository<TitreTransport, String> {
    List<TitreTransport> findTitreTransportByUsername(String username);
}
