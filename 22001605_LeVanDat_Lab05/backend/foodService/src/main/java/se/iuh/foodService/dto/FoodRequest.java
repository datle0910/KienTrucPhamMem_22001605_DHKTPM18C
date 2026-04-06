package se.iuh.foodService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {
    private String name;
    private double price;
    private String description;
}
