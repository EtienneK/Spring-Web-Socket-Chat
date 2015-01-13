package com.etiennek.chat.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and()
				.formLogin().loginPage("/login").failureUrl("/login?error")
				.permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new AuthenticationProvider() {
			@Override
			public boolean supports(Class<?> authentication) {
				return UsernamePasswordAuthenticationToken.class
						.isAssignableFrom(authentication);
			}

			@Override
			public Authentication authenticate(Authentication authentication)
					throws AuthenticationException {
				UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
				return new UsernamePasswordAuthenticationToken(token.getName(),
						token.getCredentials(), null);
			}
		});
	}

}
