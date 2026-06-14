package com.geoclinic.security;

import com.geoclinic.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private Handler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


//            http
//                    .anonymous(anonymous -> anonymous
//                            .principal("guest")           // имя principal'а
//                            .authorities("ROLE_GUEST")    // кастомная роль вместо ROLE_ANONYMOUS
//                    )
//                    .authorizeHttpRequests(auth -> auth
//                            .requestMatchers("/public/**", "/guest/**").permitAll()  // доступно всем
//                            .requestMatchers("/private/**").hasRole("USER")         // только юзеры
//                            .anyRequest().authenticated()
//                    )
//                    .formLogin(Customizer.withDefaults());
//
//            return http.build();
//        }

        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                    .anonymous(anonymous -> anonymous
                                    .principal("guest")           // имя principal'а
                                    .authorities("ROLE_GUEST")    // кастомная роль вместо ROLE_ANONYMOUS
                            )
                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/guest/**","/api/route").permitAll()
                                .requestMatchers("/registerUser").permitAll()
                                .requestMatchers("/login.html").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}