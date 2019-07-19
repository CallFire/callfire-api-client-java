package com.callfire.api.client.api.numbers.model.request;

import static lombok.AccessLevel.PROTECTED;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for searching numbers, region data, etc. which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class FindByRegionDataRequest extends FindRequest {

    /**
     * 4-7 digit prefix
     *
     * @return 4-7 digit prefix
     */
    protected String prefix;

    /**
     * City name
     *
     * @return city name
     */
    protected String city;

    /**
     * 2 letter state code
     *
     * @return 2 letter state code
     */
    protected String state;

    /**
     * 5 digit zipcode
     *
     * @return 5 digit zipcode
     */
    protected String zipcode;

    /**
     * Local access and transport area (LATA) code
     *
     * @return LATA code
     */
    protected String lata;

    /**
     * rate center code
     *
     * @return rate center code
     */
    protected String rateCenter;

    /**
     * Builder class
     */
    @SuppressWarnings("unchecked")
    public static abstract class RegionDataBuilder<B extends RegionDataBuilder, R extends FindByRegionDataRequest>
            extends FindRequestBuilder<B, R> {

        protected RegionDataBuilder(R request) {
            super(request);
        }

        /**
         * Set prefix
         *
         * @param prefix 4-7 digit prefix
         * @return builder self reference
         */
        public B prefix(String prefix) {
            request.prefix = prefix;
            return (B) this;
        }

        public B city(String city) {
            request.city = city;
            return (B) this;
        }

        /**
         * Set state
         *
         * @param state 2 letter state code
         * @return builder self reference
         */
        public B state(String state) {
            request.state = state;
            return (B) this;
        }

        /**
         * Set zipcode
         *
         * @param zipcode 5 digit zipcode
         * @return builder self reference
         */
        public B zipcode(String zipcode) {
            request.zipcode = zipcode;
            return (B) this;
        }

        /**
         * Set Local access and transport area (LATA) code
         *
         * @param lata LATA code
         * @return builder self reference
         */
        @Deprecated
        public B lata(String lata) {
            request.lata = lata;
            return (B) this;
        }

        /**
         * Set RateCenter code
         *
         * @param rateCenter RateCenter code
         * @return builder self reference
         */
        @Deprecated
        public B rateCenter(String rateCenter) {
            request.rateCenter = rateCenter;
            return (B) this;
        }
    }
}
