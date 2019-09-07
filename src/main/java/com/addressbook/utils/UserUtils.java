package com.addressbook.utils;

import java.util.Optional;

import com.addressbook.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public final class UserUtils {

    private UserUtils() {
    }

    public static Optional<Long> getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            return Optional.ofNullable(userPrincipal.getId());
        }
        return Optional.empty();
    }

    public static Optional<WebAuthenticationDetails> getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
            return Optional.ofNullable(details);
        }
        return Optional.empty();
    }
}
