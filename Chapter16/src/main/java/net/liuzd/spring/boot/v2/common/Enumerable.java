package net.liuzd.spring.boot.v2.common;

import javax.annotation.Nonnull;

public interface Enumerable<E extends Enumerable<?>> {

    /**
     * @return i18nKey
     */
    @Nonnull
    String getKey();

    /**
     * 获取最终保存到数据库的值
     * @return 值
     */
    @Nonnull
    int getValue();
   
}