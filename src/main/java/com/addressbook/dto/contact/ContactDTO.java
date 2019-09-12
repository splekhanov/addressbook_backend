package com.addressbook.dto.contact;

import com.addressbook.dto.IdentifiedDTO;
import com.addressbook.dto.security.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ContactDTO implements IdentifiedDTO {

    private static final long serialVersionUID = 3365726615380640830L;

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
