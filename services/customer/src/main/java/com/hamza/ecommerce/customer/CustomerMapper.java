package com.hamza.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public static Customer toCustommer(CustomerRequest customerRequest) {
        return Customer.builder()
                .id(customerRequest.id())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .address(customerRequest.address())
                .build();

    }
    public static CustomerRequest toCustomerRequest(Customer customer){
        return CustomerRequest.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
    public static CustomerResponse toCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
