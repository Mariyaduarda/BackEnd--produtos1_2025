package br.edu.ifmg.produto.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category") // Nome corrigido
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    // Construtor padrão
    public Category() {
    }

    // Construtor com ID e Nome
    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Construtor apenas com nome
    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
