package br.com.system.dothours.Enum;

public enum Role {

    ADMIN,
    USER;

    public String getAuthority() {
        return "ROLE_" + this.name(); // Exemplo: "ROLE_ADMIN"
    }


}
