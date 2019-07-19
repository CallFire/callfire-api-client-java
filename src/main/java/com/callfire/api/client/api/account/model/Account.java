package com.callfire.api.client.api.account.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

/**
 * User's account representation
 */
@Getter
public class Account extends CallfireModel {
    private Long id;
    private String email;
    private String name;
    private String firstName;
    private String lastName;
    private List<UserPermission> permissions = new ArrayList<>();

    public enum UserPermission {
        API,
        ACCOUNT_HOLDER,
        AGENT,
    }
}
