package com.addressbook.dto.customer;

import com.addressbook.dto.IdentifiedDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class CustomerDTO implements IdentifiedDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
