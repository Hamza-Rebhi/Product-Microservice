package com.hamza.ecommerce.customer;

import com.hamza.ecommerce.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
