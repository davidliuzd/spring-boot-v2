package net.liuzd.spring.boot.v2.config.enums;

import java.io.IOException;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import net.liuzd.spring.boot.v2.common.Enumerable;

public class EnumSerializer extends StdSerializer<Enumerable> {

    public EnumSerializer(@Nonnull Class<Enumerable> type) {
        super(type);
    }

    @Override
    public void serialize(Enumerable enumerable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("value", enumerable.getValue());
        jsonGenerator.writeStringField("text", enumerable.getKey());
        jsonGenerator.writeEndObject();
    }
}