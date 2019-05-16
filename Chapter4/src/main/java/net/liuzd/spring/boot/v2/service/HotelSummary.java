package net.liuzd.spring.boot.v2.service;

import net.liuzd.spring.boot.v2.domain.City;

public interface HotelSummary {

    City getCity();

    String getName();

    Double getAverageRating();

    default Integer getAverageRatingRounded() {
        return (getAverageRating() != null) ? (int) Math.round(getAverageRating()) : null;
    }

}