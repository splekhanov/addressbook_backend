package com.addressbook.entity.security;

import com.addressbook.entity.IdentifiedEntity;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role implements IdentifiedEntity {

    private static final long serialVersionUID = -5076844046294844849L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
