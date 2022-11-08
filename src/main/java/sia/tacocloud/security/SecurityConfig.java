package sia.tacocloud.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.data.User;
import sia.tacocloud.repository.UserRepository;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true) // For @PreAuthorize to work
public class SecurityConfig {

    /**
     * Is used when creating new users and when authenticating users at login
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    /**
     * Ensures that requests for /design and /orders are available only to authenticated
     * users; all other requests should be permitted for all users
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('USER')")
                .antMatchers("/management/**").hasRole("ADMIN")
                .antMatchers("/", "/**").access("permitAll()")

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design")

                .and()
                .oauth2Login()
                .loginPage("/login")

                // enableLogout
                .and()
                .logout()
                .logoutSuccessUrl("/")

                // disable CSRF in order not to prevent client applications from registering with the Admin Server
                .and()
                .csrf()
                .disable()
                .build();
    }
}
