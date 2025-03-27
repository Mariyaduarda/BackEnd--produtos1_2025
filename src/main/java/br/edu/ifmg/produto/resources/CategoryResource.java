package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //vai aderir requsições da web
@RequestMapping(value = "/category")

public class CategoryResource {

    @Autowired
    private CategoryRepository categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        List<Category> categories = categoryService.findAll();
        categories.add(new Category(1L, "eletronicos"));
        categories.add(new Category(2L, "jogos"));

        return ResponseEntity.ok().body(categories);
    }
}
