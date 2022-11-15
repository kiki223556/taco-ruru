package ru.tacocloud.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.tacocloud.model.auth.User;
import ru.tacocloud.repository.UserRepository;
import ru.tacocloud.security.UserDetailsService;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null)
                return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('USER')")
                .antMatchers("/", "/**").access("permitAll()")
                .and()
                .build();
    }

    // Only Tuesday has permission
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // return http
    // .authorizeRequests()
    // .antMatchers("/design", "/orders")
    // .access("hasRole('USER') && " +
    // "T(java.util.Calendar).getInstance().get(" +
    // "T(java.util.Calendar).DAY_OF_WEEK) == " +
    // "T(java.util.Calendar).TUESDAY")
    // .antMatchers("/", "/**").access("permitAll")
    // .and()
    // .build();
    // }

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
