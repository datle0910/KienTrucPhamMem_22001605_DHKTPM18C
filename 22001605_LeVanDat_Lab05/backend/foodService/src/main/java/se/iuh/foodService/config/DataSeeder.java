package se.iuh.foodService.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import se.iuh.foodService.model.Food;
import se.iuh.foodService.repository.FoodRepository;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final FoodRepository repo;

    @PostConstruct
    public void init() {
        if (repo.count() == 0) {
            repo.save(new Food(null, "Phở", 30000, "Phở bò"));
            repo.save(new Food(null, "Bún bò", 35000, "Huế"));
            repo.save(new Food(null, "Cơm tấm", 40000, "Sườn bì chả"));
        }
    }
}