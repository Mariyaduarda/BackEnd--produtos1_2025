package br.edu.ifmg.produto.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "tb_product")
public class Product {

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private Double price;

    private Instant createdAt;
    private Instant updatedAt;

    @ManyToMany
    @JoinTable( //tabela intermediaria
        name = "tb_product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> category = new HahSet<>();

    public Product() {

        public Product (String name, String description, Double price, String imageUrl){

            this.name = name;
            this.description = description;
            this.price = price;
            this.imageURL = imageURL;
        }

        /*public Product(Product entity){
            this.id = entity.get();
        }
         */
    }

}
