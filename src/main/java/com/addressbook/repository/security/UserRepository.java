package com.addressbook.repository.security;

import com.addressbook.entity.security.User;
import com.addressbook.repository.BaseRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends BaseRepository<User, Long> {

    User findByName(String userName);
}
