package com.hamza.ecommerce.payement;

import com.hamza.ecommerce.customer.CustomerResponse;
import com.hamza.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
