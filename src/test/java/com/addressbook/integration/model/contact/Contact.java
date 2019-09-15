package com.addressbook.integration.model.contact;

import com.addressbook.entity.IdentifiedEntity;
import com.addressbook.entity.security.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact implements IdentifiedEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
