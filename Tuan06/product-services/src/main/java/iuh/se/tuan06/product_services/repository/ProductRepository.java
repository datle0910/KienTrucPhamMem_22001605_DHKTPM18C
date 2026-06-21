package iuh.se.tuan06.product_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iuh.se.tuan06.product_services.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}