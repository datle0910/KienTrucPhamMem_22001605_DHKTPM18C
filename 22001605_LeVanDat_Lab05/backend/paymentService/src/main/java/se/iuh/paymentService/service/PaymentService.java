package se.iuh.paymentService.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.iuh.paymentService.dto.PaymentRequest;
import se.iuh.paymentService.dto.PaymentResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate;
    private final CircuitBreaker externalClientCircuitBreaker;
    private final Retry externalClientRetry;
    private final RateLimiter externalClientRateLimiter;
    private final TimeLimiter externalClientTimeLimiter;
    private final ScheduledExecutorService externalClientExecutor;

    public PaymentResponse processPayment(PaymentRequest request) {

        Long orderId = request.getOrderId();

        // 🔹 1. gọi Order Service update trạng thái
        String orderUrl = "http://172.16.51.206:8083/orders/" + orderId + "/paid";

        try {
            executeExternalCall(() -> {
                restTemplate.put(orderUrl, null);
                return null;
            }, "Cannot update order status");
        } catch (Exception e) {
            throw new RuntimeException("Cannot update order status");
        }

        // 🔹 2. giả lập xử lý thanh toán
        if (!request.getMethod().equalsIgnoreCase("COD") &&
                !request.getMethod().equalsIgnoreCase("BANKING")) {
            throw new RuntimeException("Invalid payment method");
        }

        // 🔹 3. gửi notification
        System.out.println("User đã thanh toán đơn #" + orderId + " thành công");

        // 🔹 4. trả response
        return new PaymentResponse(
                "Payment successful",
                orderId,
                "PAID"
        );
    }

    private <T> T executeExternalCall(Supplier<T> supplier, String errorMessage) {
        Supplier<T> withTimeLimiter = () -> externalClientTimeLimiter.executeFutureSupplier(
                () -> CompletableFuture.supplyAsync(supplier, externalClientExecutor)
        );

        Supplier<T> withRetry = Retry.decorateSupplier(externalClientRetry, withTimeLimiter);
        Supplier<T> withCircuitBreaker = CircuitBreaker.decorateSupplier(externalClientCircuitBreaker, withRetry);
        Supplier<T> withRateLimiter = RateLimiter.decorateSupplier(externalClientRateLimiter, withCircuitBreaker);

        try {
            return withRateLimiter.get();
        } catch (Exception e) {
            throw new RuntimeException(errorMessage, e);
        }
    }
}
