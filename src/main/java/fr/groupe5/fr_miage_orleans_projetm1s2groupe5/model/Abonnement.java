package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.dataencryption.JsonDateDeserializer;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.dataencryption.JsonDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transport")
public class Abonnement {

    @Id
    private String id;

    private String username;
    private String type;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private LocalDateTime dateSouscription;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private LocalDateTime dateExpiration;

    public Abonnement(String username, String type, LocalDateTime dateSouscription, LocalDateTime dateExpiration) {
        this.username = username;
        this.type = type;
        this.dateSouscription = dateSouscription;
        this.dateExpiration = dateExpiration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(LocalDateTime dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", dateSouscription=" + dateSouscription +
                ", dateExpiration=" + dateExpiration +
                '}';
    }
}
