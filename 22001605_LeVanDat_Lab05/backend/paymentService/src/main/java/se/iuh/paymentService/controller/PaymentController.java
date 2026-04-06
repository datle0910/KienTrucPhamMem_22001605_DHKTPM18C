package se.iuh.paymentService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iuh.paymentService.dto.PaymentRequest;
import se.iuh.paymentService.dto.PaymentResponse;
import se.iuh.paymentService.service.PaymentService;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public PaymentResponse pay(@RequestBody PaymentRequest request) {
        return service.processPayment(request);
    }
}