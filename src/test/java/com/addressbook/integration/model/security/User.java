package com.addressbook.integration.model.security;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private Long id;
    private String name;
    private String password;
    private boolean enabled;
    private List<Role> roles;
}
