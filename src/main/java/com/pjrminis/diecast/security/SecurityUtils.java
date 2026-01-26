package com.pjrminis.diecast.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static String getUsername() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
