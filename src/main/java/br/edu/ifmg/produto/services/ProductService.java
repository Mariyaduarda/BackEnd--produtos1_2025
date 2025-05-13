package br.edu.ifmg.produto.services;

import br.edu.ifmg.produto.dto.ProductDTO;
import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.repository.ProductRepository;
import br.edu.ifmg.produto.resources.ProductResource;
import br.edu.ifmg.produto.services.exceptions.DatabaseException;
import br.edu.ifmg.produto.services.exceptions.ResourceNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

// Importe de forma estática os métodos necessários
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    // Add this method to fix your compilation error
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDTO(x, x.getCategories()));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFound("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFound("Id not found " + id);
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFound("Id not found " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());

        entity.getCategories().clear();
        for (Long catId : dto.getCategoriesId()) {
            Category category = categoryService.getById(catId);
            entity.getCategories().add(category);
        }
    }
}