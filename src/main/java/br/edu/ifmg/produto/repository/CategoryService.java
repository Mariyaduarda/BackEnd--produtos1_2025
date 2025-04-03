package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.entities.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {  // Método correto sem @Transactional
        return categoryRepository.findAll();
    }

}
