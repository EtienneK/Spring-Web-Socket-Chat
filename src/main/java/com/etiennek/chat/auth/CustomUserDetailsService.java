package com.etiennek.chat.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    if (username == null || !"etiennek".equals(username.toLowerCase())) {
      throw new UsernameNotFoundException("No such user found");
    }

    return new UserDetails() {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean isEnabled() {
        return true;
      }

      @Override
      public boolean isCredentialsNonExpired() {
        return true;
      }

      @Override
      public boolean isAccountNonLocked() {
        return true;
      }

      @Override
      public boolean isAccountNonExpired() {
        return true;
      }

      @Override
      public String getUsername() {
        return "EtienneK";
      }

      @Override
      public String getPassword() {
        return "passw0rd";
      }

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
      }

    };
  }

}
