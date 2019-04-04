package net.liuzd.spring.boot.v2.config.enums;

import java.util.Map;
import java.util.WeakHashMap;

import javax.annotation.Nonnull;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import net.liuzd.spring.boot.v2.common.Enumerable;
import net.liuzd.spring.boot.v2.util.EnumUtils;

@SuppressWarnings("rawtypes")
public class EnumConverterFactory implements ConverterFactory<String, Enumerable> {

        private final Map<Class, Converter> converterCache = new WeakHashMap<>();

        @SuppressWarnings("unchecked")
        @Override
        public <T extends Enumerable> Converter<String, T> getConverter(@Nonnull Class<T> targetType) {
            return converterCache.computeIfAbsent(targetType,
                    k -> converterCache.put(k, new EnumConverter(k))
            );
        }

        protected class EnumConverter<T extends Enumerable> implements Converter<Integer, T> {

            private final Class<T> enumType;

            public EnumConverter(@Nonnull Class<T> enumType) {
                this.enumType = enumType;
            }

            @Override
            public T convert(@Nonnull Integer value) {
                return EnumUtils.of(this.enumType, value);
            }
        }
    }