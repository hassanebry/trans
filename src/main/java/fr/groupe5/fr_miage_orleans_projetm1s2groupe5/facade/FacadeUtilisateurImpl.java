package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.facade;


import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.EmailDejaExistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.exceptions.UtilisateurInexistantException;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Component("facadeUtilisateur")
public class FacadeUtilisateurImpl implements FacadeUtilisateur{

    @Autowired
    private final UtilisateurRepo utilisateurRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailValidator emailValidator;

    public FacadeUtilisateurImpl(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public Utilisateur inscription(Utilisateur utilisateur) throws EmailDejaExistantException {
        Optional<Utilisateur> user = utilisateurRepo.findByEmail(utilisateur.getEmail());
        boolean emailValid = emailValidator.test(utilisateur.getEmail());
        if ((user.isPresent())&&(!emailValid)){
            throw new EmailDejaExistantException();
        }

        String encodedPassword = passwordEncoder.encode(utilisateur.getPassword());
        //utilisateur.setPassword(encodedPassword);
        return utilisateurRepo.save(utilisateur);
    }

    @Override
    public Collection<Utilisateur> getAllUtilisateurs() {
        return this.utilisateurRepo.findAll();
    }

    @Override
    public int enableAdmin(String email) {
        return utilisateurRepo.enableAdmin(email);
    }

    @Override
    @Transactional
    public void updateUtilisateur(Long id, String nom, String prenom, String adresse, String telephone) throws UtilisateurInexistantException {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(()-> new UtilisateurInexistantException());

        if (nom != null && nom.length() > 0 && !Objects.equals(utilisateur.getNom(), nom)){
            utilisateur.setNom(nom);
        }

        if (prenom != null && prenom.length() > 0 && !Objects.equals(utilisateur.getEmail(), prenom)){
            utilisateur.setPrenom(prenom);
        }

        if (adresse != null && adresse.length() > 0 && !Objects.equals(utilisateur.getAdresse(), adresse)){
            utilisateur.setAdresse(adresse);
        }

        if (telephone != null && telephone.length() > 0 && !Objects.equals(utilisateur.getTelephone(), telephone)){
            utilisateur.setTelephone(adresse);
        }
    }

    @Override
    public void supprimerUtilisateur(long id) throws UtilisateurInexistantException {
        boolean exists = utilisateurRepo.existsById(id);
        if (!exists){
            throw new UtilisateurInexistantException();
        }
        utilisateurRepo.deleteById(id);
    }
}
