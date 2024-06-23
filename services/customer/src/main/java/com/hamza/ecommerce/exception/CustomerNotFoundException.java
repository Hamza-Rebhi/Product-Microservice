package com.hamza.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CustomerNotFoundException extends RuntimeException{
    private final String ms;
}
