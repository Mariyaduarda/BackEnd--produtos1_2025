package br.edu.ifmg.produto.services;

import br.edu.ifmg.produto.dto.ProductDTO;
import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.repository.ProductRepository;
import br.edu.ifmg.produto.services.exceptions.DatabaseException;
import br.edu.ifmg.produto.services.exceptions.ResourceNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired //Pra não precisar instanciar o repositório manualmente
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> list = productRepository.findAll(pageable);
        return list.map(product -> new ProductDTO(product));

    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        //o objeto vem englobado no optional
        Optional<Product> obj = productRepository.findById(id);
        Product product =
                obj.orElseThrow(
                        () ->
                                new ResourceNotFound("Product not found" + id));
        return new ProductDTO(product)
                .add(Linkto().withSelfRel());
                .add(Linkto().withRel("All products"));
                .add(Linkto().withSelfRel("Update product"));
                .add(Linkto().withSelfRel("Delete product"));


    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){

        Product entity = new Product();
        copyDtoToEntity(dto, entity);

        entity = productRepository.save(entity);

        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){

        try{
            Product entity = productRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);

            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFound("Product not found" + id);
        }

    }

    @Transactional
    public void delete(Long id){
        if (productRepository.existsById(id)){
            throw new ResourceNotFound("Product not found" + id);
        }
        try{
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }



    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());

        dto.getCategories()
                .forEach( c ->
                        entity.getCategories().add(new Category(c)));
    }

}