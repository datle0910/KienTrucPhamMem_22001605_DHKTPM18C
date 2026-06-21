package iuh.se.tuan06.customer_services.model;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
}