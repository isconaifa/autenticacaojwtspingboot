package br.com.clinicanaifa.enums;

public enum Role {
    ADMIN("Administrador"),
    USER("Usuário"),
    DOCTOR("Médico"),
    RECEPTIONIST("Recepcionista"),
    PATIENT("Paciente");

    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Spring Security espera "ROLE_ADMIN", "ROLE_USER", etc.
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
