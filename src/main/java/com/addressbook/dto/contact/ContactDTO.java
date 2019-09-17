package com.addressbook.dto.contact;

import com.addressbook.dto.IdentifiedDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ContactDTO implements IdentifiedDTO {

    private static final long serialVersionUID = 3365726615380640830L;

    @ApiModelProperty(value = "Contact ID", position = 1, hidden = true)
    private Long id;

    @ApiModelProperty(value = "First name", position = 2)
    private String firstName;

    @ApiModelProperty(value = "Last name", position = 3)
    private String lastName;

    @ApiModelProperty(value = "Phone", position = 4)
    private String phone;

    @ApiModelProperty(value = "Email", position = 5)
    private String email;

}
