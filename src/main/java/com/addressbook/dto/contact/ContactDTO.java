package com.addressbook.dto.contact;

import com.addressbook.dto.IdentifiedDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ContactDTO implements IdentifiedDTO {

    private static final long serialVersionUID = 3365726615380640830L;

    @ApiModelProperty(hidden = true)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
