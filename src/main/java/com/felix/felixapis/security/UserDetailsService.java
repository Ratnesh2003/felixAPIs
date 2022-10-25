package com.felix.felixapis.security;

import com.felix.felixapis.model.FelixUser;
import com.felix.felixapis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FelixUser felixUser = userRepository.findByUsername(username);
        if(felixUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(felixUser);
    }
}
