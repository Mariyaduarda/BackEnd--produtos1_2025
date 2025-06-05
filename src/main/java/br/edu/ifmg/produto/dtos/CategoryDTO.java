package br.edu.ifmg.produto.dtos;

import br.edu.ifmg.produto.entities.Category;

public class CategoryDTO {

    private long id;
    private String name; //

    public CategoryDTO() {

    }
    // construtor
    public CategoryDTO(Long id, String name) { //
        this.id = id;
        this.name = name;
    }



    // construtor para converter uma entidade em DTO
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
    // getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() { // Mudança de "getNome()" para "getName()"
        return name;
    }

    public void setName(String name) { // Mudança de "setNome()" para "setName()"
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name= ' " + name + '\'' +
                '}';
    }
}
