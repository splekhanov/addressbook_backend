package com.addressbook.repository.security;

import com.addressbook.entity.security.User;
import com.addressbook.repository.BaseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByName(String userName);
}
