package com.hamza.ecommerce.customer;


import com.hamza.ecommerce.customer.Address;
import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
