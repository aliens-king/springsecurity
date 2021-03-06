package com.demo.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	
	private final PasswordEncoder PasswordEncoder;
	public ApplicationSecurityConfig(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
		this.PasswordEncoder= passwordEncoder;
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.antMatchers("/", "index", "/css/*", "/js/*")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails sid = User.builder()
				.username("sudhanshu")
				.password(PasswordEncoder.encode("password"))
				.roles("STUDENT")
				.build();
		
		
		User.builder()
		.username("sid")
		.password(PasswordEncoder.encode("password123"))
		.roles("ADMIN")
		.build();
		
		return new InMemoryUserDetailsManager(sid);
	}
}
