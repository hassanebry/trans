package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;


import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.EmailDejaExistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private final UtilisateurRepo utilisateurRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailValidator emailValidator;

    public UtilisateurService(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    public Utilisateur inscription(Utilisateur utilisateur) throws EmailDejaExistantException {
        Optional<Utilisateur> user = utilisateurRepo.findByEmail(utilisateur.getEmail());
        boolean emailValid = emailValidator.test(utilisateur.getEmail());
        if ((user.isPresent())&&(!emailValid)){
            throw new EmailDejaExistantException();
        }

        String encodedPassword = passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(encodedPassword);
        return utilisateurRepo.save(utilisateur);
    }
}
