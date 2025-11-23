package com.empman.ems.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Value("${spring.security.user.name}")
    private String defaultUser;

    @Value("${spring.security.user.password}")
    private String defaultPassword;

    private final PasswordEncoder passwordEncoder;

    public JwtUserDetailsService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Simple: return the single configured user from application.properties
        if (defaultUser.equals(username)) {
            return User.withUsername(defaultUser)
                    .password(passwordEncoder.encode(defaultPassword))
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}