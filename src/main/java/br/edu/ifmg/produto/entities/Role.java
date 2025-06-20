package br.edu.ifmg.produto.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public Role() { }

    public Role(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String getAuthority() {
        return "";
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Role)) return false;
        Category role = null;
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" +
                authority + '\'' +
                '}';
    }
}
