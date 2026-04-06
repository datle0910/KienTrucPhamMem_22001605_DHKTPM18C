package se.iuh.paymentService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.iuh.paymentService.dto.PaymentRequest;
import se.iuh.paymentService.dto.PaymentResponse;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate;

    public PaymentResponse processPayment(PaymentRequest request) {

        Long orderId = request.getOrderId();

        // 🔹 1. gọi Order Service update trạng thái
        String orderUrl = "http://172.16.51.206:8083/orders/" + orderId + "/paid";

        try {
            restTemplate.put(orderUrl, null);
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
}
