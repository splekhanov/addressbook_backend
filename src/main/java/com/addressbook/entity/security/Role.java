package com.addressbook.entity.security;

import com.addressbook.entity.IdentifiedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Role(String name){
        this.name = name;
    }
}
