package com.callfire.api.client.api.numbers.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for searching numbers, region data, etc. which incapsulates
 * different query pairs
 */
public abstract class FindByRegionDataRequest extends FindRequest {
    protected String prefix;
    protected String city;
    protected String state;
    protected String zipcode;
    protected String lata;
    protected String rateCenter;

    protected FindByRegionDataRequest() {
    }

    /**
     * Get 4-7 digit prefix
     *
     * @return 4-7 digit prefix
     */
    public String getPrefix() {
        return prefix;
    }

    public String getCity() {
        return city;
    }

    /**
     * Get 2 letter state code
     *
     * @return 2 letter state code
     */
    public String getState() {
        return state;
    }

    /**
     * Get 5 digit zipcode
     *
     * @return 5 digit zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Get LATA code
     *
     * @return LATA code
     */
    public String getLata() {
        return lata;
    }

    /**
     * Get rate center code
     *
     * @return rate center code
     */
    public String getRateCenter() {
        return rateCenter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("prefix", prefix)
            .append("city", city)
            .append("state", state)
            .append("zipcode", zipcode)
            .append("lata", lata)
            .append("rateCenter", rateCenter)
            .toString();
    }

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
         */
        public B state(String state) {
            request.state = state;
            return (B) this;
        }

        /**
         * Set zipcode
         *
         * @param zipcode 5 digit zipcode
         */
        public B zipcode(String zipcode) {
            request.zipcode = zipcode;
            return (B) this;
        }

        /**
         * Set LATA code
         *
         * @param lata LATA code
         */
        public B lata(String lata) {
            request.lata = lata;
            return (B) this;
        }

        /**
         * Set RateCenter code
         *
         * @param rateCenter RateCenter code
         */
        public B rateCenter(String rateCenter) {
            request.rateCenter = rateCenter;
            return (B) this;
        }
    }
}
