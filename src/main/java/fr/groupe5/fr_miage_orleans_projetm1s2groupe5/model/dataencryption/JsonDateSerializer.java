package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.model.dataencryption;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonDateSerializer<L> extends StdSerializer<LocalDateTime> {

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public JsonDateSerializer() {
        this(null);
    }

    protected JsonDateSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalDateTime value,
            JsonGenerator gen,
            SerializerProvider arg2)
            throws IOException, JsonProcessingException {

        gen.writeString(formatter.format(value));
    }

}
