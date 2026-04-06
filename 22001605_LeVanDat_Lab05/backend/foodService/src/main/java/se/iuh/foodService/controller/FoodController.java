package se.iuh.foodService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.iuh.foodService.dto.FoodRequest;
import se.iuh.foodService.dto.FoodResponse;
import se.iuh.foodService.model.Food;
import se.iuh.foodService.service.FoodService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService service;

    @GetMapping
    public List<FoodResponse> getAll() {
        return service.getAll().stream()
                .map(f -> new FoodResponse(
                        f.getId(),
                        f.getName(),
                        f.getPrice(),
                        f.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FoodResponse getById(@PathVariable Long id) {
        Food f = service.getById(id);
        return new FoodResponse(f.getId(), f.getName(), f.getPrice(), f.getDescription());
    }

    @PostMapping
    public FoodResponse create(@RequestBody FoodRequest req) {
        Food food = new Food(null, req.getName(), req.getPrice(), req.getDescription());
        Food saved = service.create(food);
        return new FoodResponse(saved.getId(), saved.getName(), saved.getPrice(), saved.getDescription());
    }

    @PutMapping("/{id}")
    public FoodResponse update(@PathVariable Long id, @RequestBody FoodRequest req) {
        Food food = new Food(null, req.getName(), req.getPrice(), req.getDescription());
        Food updated = service.update(id, food);
        return new FoodResponse(updated.getId(), updated.getName(), updated.getPrice(), updated.getDescription());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
