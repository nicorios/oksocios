package com.oksocios.config;

import com.oksocios.repository.UserRepository;
import com.oksocios.service.UserDetailsServiceImpl;
import com.oksocios.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(bCryptPasswordEncoder());
        auth.userDetailsService(userDetailsServiceBean());
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new UserDetailsServiceImpl(userRepository);
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/users/**", "/establishments/**", "/home/**", "/stats/**", "/customers/**", "/settings/**", "/balance").hasAuthority(Constants.ROLE_NAME_ADMIN)
                .antMatchers("/users/**", "/establishments/**", "/home/**", "/customers/**").hasAnyAuthority(Constants.ROLE_NAME_ADMIN, Constants.ROLE_NAME_EMPLOYEE)
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/establishments", true).failureUrl("/login-error");

        http.csrf().disable();
    }

}
