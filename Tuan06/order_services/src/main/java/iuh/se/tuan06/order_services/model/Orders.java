package main.java.iuh.se.tuan06.order_services.model;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;
    private Double totalPrice;
}