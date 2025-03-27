//PRIMEIRA CAMADA - A MAIS ABAIXO
package br.edu.ifmg.produto.entities;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name="ID_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private long id;
    //@Column(unique = true) //referencia ao id, smp abaixo do referncial
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(long id) {
        this.id = id;
    }

    public Category(long l, String jogos) {
    }

    public Category() {

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
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
