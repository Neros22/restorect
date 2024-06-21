package com.skgworks.restorect.model;

import lombok.Getter;

@Getter
public enum EUserPermission {
    USER_READ("user:read"), USER_WRITE("user:write"), ADMIN_READ("admin:read"), ADMIN_WRITE("admin:write");

    private final String permission;

    EUserPermission(String permission) {
        this.permission = permission;
    }

}
