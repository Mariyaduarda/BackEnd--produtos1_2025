package br.edu.ifmg.produto.util;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.entities.Category;

import static java.awt.AWTEventMulticaster.add;

public class Factory {
    public static <Procuct> Procuct createProduct (){
        Product p = new Product();
        p.setName("Iphone XXX");
        p.setPrice(5000);
        p.setImageUrl("https://i1.sndcdn.com/artworks-VUPODJGHzFVyhXgV-ccH6ug-t500x500.jpg");
        p.getCategories()
                .add(new Category(1L, "LIVROS"));
        return (Procuct) p;
    }

    public static ProductDTO createProductDTO (){
        Product p = createProduct();
        return new ProductDTO(p);
    }
}
