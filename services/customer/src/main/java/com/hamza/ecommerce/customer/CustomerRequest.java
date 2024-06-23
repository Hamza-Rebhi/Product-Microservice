package com.hamza.ecommerce.customer;

import com.hamza.ecommerce.customer.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder

public record CustomerRequest(
        String id,
        @NotNull(message = "firstname is required")
        String firstName,
         @NotNull(message = "lastname is required")
         String lastName,
         @NotNull(message = "email is required")
         @Email(message = "not a valid email")
         String email,
          Address address
) {
}
