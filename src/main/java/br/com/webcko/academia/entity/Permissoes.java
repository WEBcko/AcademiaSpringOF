package br.com.webcko.academia.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissoes {
    //define diferentes tipos de permissoes no sistema
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    PERSONAL_READ("personal:read"),
    PERSONAL_UPDATE("personal:update"),
    PERSONAL_CREATE("personal:create"),
    ;

    @Getter
    private final String permission;
}
