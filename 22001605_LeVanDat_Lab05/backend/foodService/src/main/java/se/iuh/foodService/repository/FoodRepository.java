package se.iuh.foodService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iuh.foodService.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}