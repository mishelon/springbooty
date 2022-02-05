package com.mishelon.base.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mishelon
 * @since 5 feb 2022
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth.inMemoryAuthentication().withUser(UserRol.ADMIN.name()).password(encoder.encode("admin"))
        .roles("ADMIN").and().withUser("user").password(encoder.encode("user"))
        .roles(UserRol.USER.name());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
        .disable().authorizeRequests().antMatchers(HttpMethod.GET, "/findById/*").authenticated()
        .antMatchers(HttpMethod.DELETE, "/country/*").hasRole(UserRol.ADMIN.name())
        .antMatchers("/**").permitAll().and().httpBasic();
  }

}
