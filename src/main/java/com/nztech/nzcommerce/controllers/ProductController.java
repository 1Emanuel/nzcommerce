package com.nztech.nzcommerce.controllers;

import com.nztech.nzcommerce.dto.ProductDTO;
import com.nztech.nzcommerce.entities.Product;
import com.nztech.nzcommerce.repositories.ProductRepository;
import com.nztech.nzcommerce.serviceses.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
         return service.findById(id);

    }
}
