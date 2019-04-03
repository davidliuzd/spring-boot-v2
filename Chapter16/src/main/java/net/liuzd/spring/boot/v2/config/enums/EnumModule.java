package net.liuzd.spring.boot.v2.config.enums;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import net.liuzd.spring.boot.v2.common.Enumerable;
import net.liuzd.spring.boot.v2.util.EnumUtil;

/**
 * @author shiyajian
 * create: 2019-03-21
 */
public class EnumModule extends SimpleModule {

    public EnumModule() {
        super("jacksonEnumTypeModule", Version.unknownVersion());
        this.setDeserializers(new CustomDeserializers());
        this.addSerializer(new EnumSerializer());
    }

    private static class CustomDeserializers extends SimpleDeserializers {
        private CustomDeserializers() {
        }

        @Override
        @SuppressWarnings({"rawtypes", "unchecked"})
        public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
            return Enumerable.class.isAssignableFrom(type) ?
                    new EnumDeserializer(type) :
                    super.findEnumDeserializer(type, config, beanDesc);
        }

        private static class EnumDeserializer<E extends Enumerable> extends StdScalarDeserializer<E> {

            private Class<E> enumType;

            private EnumDeserializer(Class<E> clazz) {
                super(clazz);
                this.enumType = clazz;
            }

            @Override
            public E deserialize(JsonParser parser, DeserializationContext context) throws IOException {
                // 前台如果传递只传value
                if (parser.getCurrentToken().isNumeric()) {
                    return EnumUtil.of(this.enumType, parser.getIntValue());

                }
                // 前台以对象形式传递
                TreeNode node = parser.getCodec().readTree(parser).get("value");
                if (!(node instanceof IntNode)) {
                    throw new IllegalArgumentException(" enum value not numeric type");
                }
                IntNode intNode = (IntNode) node;
                return EnumUtil.of(this.enumType, intNode.intValue());

            }
        }
    }

    private static class EnumSerializer extends StdSerializer<Enumerable> {

        private EnumSerializer() {
            super(Enumerable.class);
        }

        @Override
        public void serialize(Enumerable enumerable, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("value", enumerable.getValue());
            jsonGenerator.writeStringField("text", enumerable.getKey());
            jsonGenerator.writeEndObject();
        }
    }
}
