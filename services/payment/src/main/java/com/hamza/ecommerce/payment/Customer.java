package com.hamza.ecommerce.payment;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Firstname is required")
        String firstname,
        @NotNull(message = "lastname is required")
        String lastname,
        @NotNull(message = "mail is required")
        String mail
) {
}
