package br.edu.ifmg.produto.dto;

import br.edu.ifmg.produto.entities.Category;

public class CategoryDTO {
    private long id;
    private String name; // Mudança de "nome" para "name"

    // Construtor correto
    public CategoryDTO(Long id, String name) { // Ajustado para "name"
        this.id = id;
        this.name = name;
    }

    // Construtor para converter uma entidade em DTO
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName(); // Agora os nomes batem
    }

    // Getters e Setters
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
}
