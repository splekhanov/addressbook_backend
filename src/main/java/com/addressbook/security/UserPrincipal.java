package com.addressbook.security;

import com.addressbook.entity.security.Role;
import com.addressbook.entity.security.User;
import com.addressbook.utils.gson.GsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserPrincipal implements UserDetails {

    private Long id;
    private String name;

    @GsonIgnore
    private String password;
    private List<Role> roles;

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user.getId(), user.getName(), user.getPassword(), user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return User.builder()
                .id(id)
                .name(name)
                .password(password)
                .roles(roles)
                .build();
    }

}
