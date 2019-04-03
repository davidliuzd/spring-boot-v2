package net.liuzd.spring.boot.v2.config.enums;

import java.io.IOException;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import net.liuzd.spring.boot.v2.common.Enumerable;
import net.liuzd.spring.boot.v2.util.EnumUtil;

public class EnumDeserializer<E extends Enumerable> extends StdDeserializer<E> {

    private Class<E> enumType;

    public EnumDeserializer(@Nonnull Class<E> enumType) {
        super(enumType);
        this.enumType = enumType;
    }

    @Override
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return EnumUtil.of(this.enumType, jsonParser.getIntValue());
    }

}