package se.iuh.orderService.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private List<Long> foodIds;
    private double totalPrice;
    private String status; // CREATED, PAID
}