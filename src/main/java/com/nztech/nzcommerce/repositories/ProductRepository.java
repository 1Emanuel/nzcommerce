package com.nztech.nzcommerce.repositories;

import com.nztech.nzcommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
