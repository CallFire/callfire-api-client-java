package com.callfire.api.client.api.account.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiCredentials extends CallfireModel {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean enabled;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("name", name)
            .append("username", username)
            .append("password", password)
            .append("enabled", enabled)
            .toString();
    }
}
