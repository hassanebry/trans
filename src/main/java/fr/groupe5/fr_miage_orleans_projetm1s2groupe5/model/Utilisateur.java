package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.dataencryption.JsonDateDeserializer;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.dataencryption.JsonDateSerializer;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;



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

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public LocalDateTime getDateInsc() {
        return LocalDateTime.now();
    }

    public void setDateInsc() {
        this.dateInsc = LocalDateTime.now();
    }
}
