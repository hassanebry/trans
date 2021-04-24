package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private long id;

    private String nom;
    private String prenom;
    private String adresse;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String telephone;
    private boolean admin;
    private LocalDateTime dateInsc;

    public Utilisateur(String nom, String prenom, String adresse, String email, String password, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.dateInsc = LocalDateTime.now();
        this.admin = false;
    }
}
