package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.dataencryption.JsonDateDeserializer;
import fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.dataencryption.JsonDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transport")
public class TitreTransport {

    @Id
    private String id;
    private String type;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private LocalDateTime dateExpiration;

    private boolean valid;


    public TitreTransport(String type, LocalDateTime dateExpiration) {
        this.type = type;
        this.dateExpiration = dateExpiration;
        this.valid = false;
    }

    public TitreTransport() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "TitreTransport{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", dateExpiration=" + dateExpiration +
                '}';
    }
}
