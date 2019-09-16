package com.addressbook.integration.model.contact;

import com.addressbook.entity.IdentifiedEntity;
import com.addressbook.entity.security.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Contact implements IdentifiedEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
