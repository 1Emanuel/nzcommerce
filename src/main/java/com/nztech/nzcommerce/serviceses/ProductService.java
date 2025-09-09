package com.nztech.nzcommerce.serviceses;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.nztech.nzcommerce.dto.ProductDTO;
import com.nztech.nzcommerce.entities.Product;
import com.nztech.nzcommerce.repositories.ProductRepository;
import com.nztech.nzcommerce.serviceses.exceptions.DatabaseException;
import com.nztech.nzcommerce.serviceses.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);
        Product product = result.orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado"));
        return new ProductDTO(product);

    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        copyDtoToEntity(productDTO, product);
        product = repository.save(product);
        return new ProductDTO(product);

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
            try {
                Product product = repository.getReferenceById(id);
                copyDtoToEntity(productDTO, product);
                product = repository.save(product);
                return new ProductDTO(product);
            }catch (EntityNotFoundException e) {
                throw new ResourceNotFoundException("Recurso nao encontrado");
            }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
       try {
           repository.deleteById(id);
       }
       catch (DataIntegrityViolationException e) {
           throw new DatabaseException("Falha de integridade referencial");
       }
    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImgUrl(productDTO.getImgUrl());
        product.setPrice(productDTO.getPrice());

    }




}
