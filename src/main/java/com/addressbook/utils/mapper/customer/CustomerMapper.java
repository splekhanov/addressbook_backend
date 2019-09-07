package com.addressbook.utils.mapper.customer;

import com.addressbook.dto.customer.CustomerDTO;
import com.addressbook.entity.customer.Customer;
import com.addressbook.utils.mapper.EntityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDTO> {

    @Override
    public CustomerDTO toDto(Customer source) {
        if (source == null) {
            return null;
        }
        CustomerDTO target = CustomerDTO.builder().build();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    @Override
    public Customer toEntity(CustomerDTO source) {
        if (source == null) {
            return null;
        }
        Customer target = Customer.builder().build();
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
