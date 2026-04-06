package se.iuh.orderService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.iuh.orderService.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestTemplate restTemplate;

    private final List<Order> orders = new ArrayList<>();
    private Long idCounter = 1L;

    // tạo order
    public Order create(Long userId, List<Long> foodIds) {

        // 1. validate user
        String userUrl = "http://172.16.51.206:8081/users/" + userId;
        try {
            restTemplate.getForObject(userUrl, Object.class);
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }

        // 2. lấy thông tin food + tính tiền
        double total = 0;

        for (Long foodId : foodIds) {
            String foodUrl = "http://172.16.51.206:8082/foods/" + foodId;

            Map<String, Object> food;
            try {
                food = restTemplate.getForObject(foodUrl, Map.class);
            } catch (Exception e) {
                throw new RuntimeException("Food not found: " + foodId);
            }

            total += Double.parseDouble(food.get("price").toString());
        }

        // 3. tạo order
        Order order = new Order();
        order.setId(idCounter++);
        order.setUserId(userId);
        order.setFoodIds(foodIds);
        order.setTotalPrice(total);
        order.setStatus("CREATED");

        orders.add(order);

        return order;
    }

    // lấy tất cả
    public List<Order> getAll() {
        return orders;
    }

    // lấy theo id
    public Order getById(Long id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // update status
    public void markPaid(Long id) {
        Order order = getById(id);
        order.setStatus("PAID");
    }
}
