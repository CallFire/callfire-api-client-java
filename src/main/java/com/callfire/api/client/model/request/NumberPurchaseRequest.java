package com.callfire.api.client.model.request;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class NumberPurchaseRequest extends BaseModel {
    private Integer tollFreeCount;
    private Integer localCount;
    private String prefix;
    private String city;
    private String state;
    private String zipcode;
    private String lata;
    private String rateCenter;
    private List<String> numbers = new ArrayList<>();

    private NumberPurchaseRequest() {
    }

    public static Builder create() {
        return new Builder();
    }

    public Integer getTollFreeCount() {
        return tollFreeCount;
    }

    public Integer getLocalCount() {
        return localCount;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getLata() {
        return lata;
    }

    public String getRateCenter() {
        return rateCenter;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("tollFreeCount", tollFreeCount)
            .append("localCount", localCount)
            .append("prefix", prefix)
            .append("city", city)
            .append("state", state)
            .append("zipcode", zipcode)
            .append("lata", lata)
            .append("rateCenter", rateCenter)
            .append("numbers", numbers)
            .toString();
    }

    public static class Builder {
        private NumberPurchaseRequest request;

        private Builder() {
            request = new NumberPurchaseRequest();
        }

        public Builder setTollFreeCount(Integer tollFreeCount) {
            request.tollFreeCount = tollFreeCount;
            return this;
        }

        public Builder setNumbers(List<String> numbers) {
            request.numbers = numbers;
            return this;
        }

        public Builder setRateCenter(String rateCenter) {
            request.rateCenter = rateCenter;
            return this;
        }

        public Builder setLocalCount(Integer localCount) {
            request.localCount = localCount;
            return this;
        }

        public Builder setPrefix(String prefix) {
            request.prefix = prefix;
            return this;
        }

        public Builder setCity(String city) {
            request.city = city;
            return this;
        }

        public Builder setLata(String lata) {
            request.lata = lata;
            return this;
        }

        public Builder setZipcode(String zipcode) {
            request.zipcode = zipcode;
            return this;
        }

        public Builder setState(String state) {
            request.state = state;
            return this;
        }

        public NumberPurchaseRequest build() {
            return request;
        }
    }
}
