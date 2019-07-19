package com.callfire.api.client.api.contacts.model;

import java.util.HashMap;
import java.util.Map;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents contact information in Callfire system
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default private Map<String, String> properties = new HashMap<>();
}
