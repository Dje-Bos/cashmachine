package org.sut.cashmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.model.user.UserModel;
import org.sut.cashmachine.security.DefaultUserDetails;

@Service
public class DefaultUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public DefaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        else {
            return DefaultUserDetails.of(user);
        }
    }
}
