package com.addressbook.integration.model.security;

import com.addressbook.entity.IdentifiedEntity;
import com.addressbook.entity.security.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class Role {

    private String name;

    public Role(String name) {
        this.name = name;
    }
}
