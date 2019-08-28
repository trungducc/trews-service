package common.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TrewsErrorSerializer extends JsonSerializer<TrewsError> {

    @Override
    public void serialize(TrewsError trewsError, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("code");
        gen.writeNumber(trewsError.getCode());
        gen.writeFieldName("message");
        gen.writeString(trewsError.getMessage());
        gen.writeEndObject();
    }

}