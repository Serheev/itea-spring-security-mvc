package com.serheev.config.security;

import com.serheev.controller.RoleSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("anonym").password(passwordEncoder().encode("anonym")).roles("ANONYM");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("LESSON_USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("LESSON_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(new RoleSecurityFilter(), BasicAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/opened/**").permitAll()
                .antMatchers("/closed/**").access("hasAnyRole('ROLE_LESSON_USER', 'ROLE_LESSON_ADMIN')")
                .antMatchers("/protected/**").access("hasRole('ROLE_LESSON_ADMIN')")
                .and().formLogin().defaultSuccessUrl("/", false);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

}
