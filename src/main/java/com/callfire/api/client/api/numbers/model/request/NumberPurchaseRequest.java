package com.callfire.api.client.api.numbers.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class NumberPurchaseRequest extends CallfireModel {
    private Integer tollFreeCount;
    private Integer localCount;
    private String prefix;
    private String city;
    private String state;
    private String zipcode;
    private String lata;
    private String rateCenter;
    private List<String> numbers = new ArrayList<>();

    public static Builder create() {
        return new Builder();
    }

    public static class Builder extends AbstractBuilder<NumberPurchaseRequest> {

        private Builder() {
            super(new NumberPurchaseRequest());
        }

        public Builder tollFreeCount(Integer tollFreeCount) {
            request.tollFreeCount = tollFreeCount;
            return this;
        }

        public Builder numbers(List<String> numbers) {
            request.numbers = numbers;
            return this;
        }

        public Builder rateCenter(String rateCenter) {
            request.rateCenter = rateCenter;
            return this;
        }

        public Builder localCount(Integer localCount) {
            request.localCount = localCount;
            return this;
        }

        public Builder prefix(String prefix) {
            request.prefix = prefix;
            return this;
        }

        public Builder city(String city) {
            request.city = city;
            return this;
        }

        public Builder lata(String lata) {
            request.lata = lata;
            return this;
        }

        public Builder zipcode(String zipcode) {
            request.zipcode = zipcode;
            return this;
        }

        public Builder state(String state) {
            request.state = state;
            return this;
        }
    }
}
