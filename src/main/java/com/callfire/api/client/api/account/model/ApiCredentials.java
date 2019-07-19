package com.callfire.api.client.api.account.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiCredentials extends CallfireModel {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean enabled;

    public ApiCredentials(String name) {
        this.name = name;
    }
}
