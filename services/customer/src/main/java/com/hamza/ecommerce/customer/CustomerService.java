package com.hamza.ecommerce.customer;

import com.hamza.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public String createCustomer(CustomerRequest customerRequest){
         return customerRepository.save(customerMapper.toCustommer(customerRequest)).getId();
    }
    public void updateCustomer(CustomerRequest customerRequest){
       var customer= customerRepository.findById(customerRequest
                .id())
                .orElseThrow( ()->new CustomerNotFoundException(
                        String
                        .format("cannot update customer :: no customer provided with this ID :: %s",customerRequest.id())));
       mergerCustomer(customer,customerRequest);
       customerRepository.save(customer);
    }
    public Boolean existsById(String id){

        return  customerRepository.existsById(id);
    }
    public CustomerResponse findById(String customerId){
      return customerRepository.findById(customerId).map(CustomerMapper::toCustomerResponse)
              .orElseThrow(()->new CustomerNotFoundException
                      (String.format("No customer provided with this id",customerId)));
    }
    public void  deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    private void mergerCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setLastName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address()!=null){
            customer.setAddress(customerRequest.address());
        }
    }
    public List<CustomerResponse> findAllCustomers(){
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerResponse).collect(Collectors.toList());
    }
}
