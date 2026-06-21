package iuh.se.tuan06.product_services.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double price;
    private Integer stock;
}
