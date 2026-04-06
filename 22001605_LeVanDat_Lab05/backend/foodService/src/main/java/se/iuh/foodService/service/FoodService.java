package se.iuh.foodService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iuh.foodService.model.Food;
import se.iuh.foodService.repository.FoodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository repo;

    public List<Food> getAll() {
        return repo.findAll();
    }

    public Food getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }

    public Food create(Food food) {
        return repo.save(food);
    }

    public Food update(Long id, Food newFood) {
        Food food = getById(id);
        food.setName(newFood.getName());
        food.setPrice(newFood.getPrice());
        food.setDescription(newFood.getDescription());
        return repo.save(food);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}