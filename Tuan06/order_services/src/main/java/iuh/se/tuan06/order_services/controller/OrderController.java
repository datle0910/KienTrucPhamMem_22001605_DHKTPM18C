package main.java.iuh.se.tuan06.order_services.controller;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repo;
    private final RestTemplate restTemplate;

    @PostMapping
    public Orders create(@RequestBody Orders order) {

        // gọi Customer Service
        restTemplate.getForObject(
            "http://customer-service:8082/customers/" + order.getCustomerId(),
            Object.class
        );

        return repo.save(order);
    }

    @GetMapping
    public List<Orders> getAll() {
        return repo.findAll();
    }
}