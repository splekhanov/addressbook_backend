package com.addressbook.repository.security;

import com.addressbook.entity.security.Role;
import com.addressbook.repository.BaseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String role);
}
