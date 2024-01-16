package com.JuanHDSM.Vendas.domain.entities.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("users");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
