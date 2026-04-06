package se.iuh.orderService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.iuh.orderService.dto.CreateOrderRequest;
import se.iuh.orderService.dto.OrderResponse;
import se.iuh.orderService.model.Order;
import se.iuh.orderService.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest req) {
        Order order = service.create(req.getUserId(), req.getFoodIds());

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getFoodIds(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }

    @GetMapping
    public List<OrderResponse> getAll() {
        return service.getAll().stream()
                .map(o -> new OrderResponse(
                        o.getId(),
                        o.getUserId(),
                        o.getFoodIds(),
                        o.getTotalPrice(),
                        o.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        Order o = service.getById(id);

        return new OrderResponse(
                o.getId(),
                o.getUserId(),
                o.getFoodIds(),
                o.getTotalPrice(),
                o.getStatus()
        );
    }

    @PutMapping("/{id}/paid")
    public void markPaid(@PathVariable Long id) {
        service.markPaid(id);
    }
}
