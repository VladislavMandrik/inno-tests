package org.example.enums;

import org.example.config.EnvConfig;

public enum Role {
    ADMIN,
    USER;

    public String getRoleName() {
        return "ROLE_" + name();
    }

    public String getUsername() {
        return EnvConfig.get(name() + "_USERNAME");
    }

    public String getPassword() {
        return EnvConfig.get(name() + "_PASSWORD");
    }
}