package ru.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    // List<UserDetails> usersList = new ArrayList<>();
    // usersList.add(new User(
    // "buzz", encoder.encode("password"),
    // Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    // usersList.add(new User(
    // "woody", encoder.encode("password"),
    // Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    // return new InMemoryUserDetailsManager(usersList);
    // }

}
