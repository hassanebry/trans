package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.security;



import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.Utilisateur;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.repo.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


public class CustomUserDetailsService implements UserDetailsService {
    private static final String[] ROLES_ADMIN = {"USER", "ADMIN"};
    private static final String[] ROLES_USER = {"USER"};

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = utilisateurRepo.findByUsername(s);
        if (utilisateur.isEmpty()){
            throw new UsernameNotFoundException("l'utilisateur ayant le pseudo:  " +s+ " n'existe pas");
        }
        String[] roles = utilisateur.get().isAdmin() ? ROLES_ADMIN : ROLES_USER;

        UserDetails userDetails = User.builder()
                .username(utilisateur.get().getUsername())
                .password(passwordEncoder.encode(utilisateur.get().getPassword()))
                .roles(roles)
                .build();

        return userDetails ;
    }
}
