package com.calculation.security.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calculation.Repository.UserRepository;
import com.calculation.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;


  @Override
  @Transactional
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userRepository.findByName(name).orElse(null);
    

    if (user != null) {
      return UserDetailsImpl.buildUser(user);
    
    } else {
      throw new UsernameNotFoundException("User Not Found with username: " + name);
    }
  }
}
