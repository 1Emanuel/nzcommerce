package com.nztech.nzcommerce.serviceses;

import com.nztech.nzcommerce.dto.ProductDTO;
import com.nztech.nzcommerce.entities.Product;
import com.nztech.nzcommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
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
        Product product = result.get();
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
        Product product = repository.getReferenceById(id);
        copyDtoToEntity(productDTO, product);
        product = repository.save(product);
        return new ProductDTO(product);

    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImgUrl(productDTO.getImgUrl());
        product.setPrice(productDTO.getPrice());

    }




}
