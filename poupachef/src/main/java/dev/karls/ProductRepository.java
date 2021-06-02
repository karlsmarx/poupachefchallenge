package dev.karls;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.karls.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

