package ru.tacocloud.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    // functional interface
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
