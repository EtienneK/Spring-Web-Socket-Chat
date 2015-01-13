package com.etiennek.chat.config;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .anyRequest()
        .fullyAuthenticated()
        .and()
        .formLogin()
        .authenticationDetailsSource(new CustomWebAuthenticationDetailsSource())
        .loginPage("/login")
        .failureUrl("/login?error")
        .permitAll();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(new AuthenticationProvider() {
      @Override
      public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
      }

      @Override
      public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        return new UsernamePasswordAuthenticationToken(token.getName(), token.getCredentials(), null);
      }
    });
  }

  private class CustomWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
      return new CustomWebAuthenticationDetails(context);
    }
  }

  private class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 4600395938939186132L;

    private String roomName;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
      super(request);
      roomName = request.getParameter("roomName");
    }

    public String getRoomName() {
      return roomName;
    }

  }

}
