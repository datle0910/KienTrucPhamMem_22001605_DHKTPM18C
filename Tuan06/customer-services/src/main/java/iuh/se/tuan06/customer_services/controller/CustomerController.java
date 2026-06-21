package iuh.se.tuan06.customer_services.controller;

@RestController

@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repo;

    @PostMapping
    public Customer create(@RequestBody Customer c) {
        return repo.save(c);
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }
}