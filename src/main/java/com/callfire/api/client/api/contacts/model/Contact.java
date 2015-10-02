package com.callfire.api.client.api.contacts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents contact information in Callfire system
 */
public class Contact extends CallfireModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String zipcode;
    private String homePhone;
    private String workPhone;
    private String mobilePhone;
    private String externalId;
    private String externalSystem;
    private Boolean deleted;
    private Map<String, String> properties = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * Get external id of contact for syncing with external sources
     *
     * @return external id of contact for syncing with external sources
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Set external id of contact for syncing with external sources
     *
     * @param externalId external id of contact for syncing with external sources
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * Get external system that external id refers to
     *
     * @return external system that external id refers to
     */
    public String getExternalSystem() {
        return externalSystem;
    }

    /**
     * Set external system that external id refers to
     *
     * @param externalSystem external system that external id refers to
     */
    public void setExternalSystem(String externalSystem) {
        this.externalSystem = externalSystem;
    }

    /**
     * Get map of string properties for contact
     *
     * @return map of string properties for contact
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * Set map of string properties for contact
     *
     * @param properties set map of string properties for contact
     */
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * Is contact deleted
     *
     * @return true if contact was deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Set true if contact was deleted
     *
     * @param deleted true if contact was deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .append("zipcode", zipcode)
            .append("homePhone", homePhone)
            .append("workPhone", workPhone)
            .append("mobilePhone", mobilePhone)
            .append("externalId", externalId)
            .append("externalSystem", externalSystem)
            .append("properties", properties)
            .append("deleted", deleted)
            .toString();
    }
}
