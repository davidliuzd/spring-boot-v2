package net.liuzd.spring.boot.v2.service;

import net.liuzd.spring.boot.v2.domain.enums.Rating;

public interface RatingCount {

    Rating getRating();

    long getCount();

}