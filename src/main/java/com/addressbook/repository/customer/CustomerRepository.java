package com.addressbook.repository.customer;

import com.addressbook.entity.customer.Customer;
import com.addressbook.repository.BaseRepository;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository extends BaseRepository<Customer, Long> {
}
