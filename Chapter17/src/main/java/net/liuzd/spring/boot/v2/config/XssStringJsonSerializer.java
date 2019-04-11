package net.liuzd.spring.boot.v2.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.web.util.XssUtil;

/**
 * 描述 : 基于xss的JsonSerializer 涉及到json转换的地方,也一样需要进行转意,比如,rerquestBody,responseBody
 */
@Slf4j
public class XssStringJsonSerializer extends JsonSerializer<String> {

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (value != null) {
            String encodedValue = XssUtil.cleanXSS(value);
            log.debug("JsonSerializer ，old： {} ，new : {}", value, encodedValue);
            jsonGenerator.writeString(encodedValue);
        }
    }

}