package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Region extends BaseModel {
    private String prefix;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String lata;
    private String rateCenter;
    private Double latitude;
    private Double longitude;
    private String timeZone;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLata() {
        return lata;
    }

    public void setLata(String lata) {
        this.lata = lata;
    }

    public String getRateCenter() {
        return rateCenter;
    }

    public void setRateCenter(String rateCenter) {
        this.rateCenter = rateCenter;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("prefix", prefix)
            .append("city", city)
            .append("state", state)
            .append("zipcode", zipcode)
            .append("country", country)
            .append("lata", lata)
            .append("rateCenter", rateCenter)
            .append("latitude", latitude)
            .append("longitude", longitude)
            .append("timeZone", timeZone)
            .toString();
    }
}
