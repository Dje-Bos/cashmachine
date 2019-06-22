package org.sut.cashmachine.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.sut.cashmachine.dao.user.DataJpaUserRepository;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.model.user.UserModel;
import org.sut.cashmachine.security.UserPrincipal;

@Service
public class DefaultUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public DefaultUserDetailsService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.getUserByEmail(username);
        return validateAndReturn(user, username);
    }

    public UserDetails loadUserById(long id) {
        UserModel userModel = userRepository.getUserById(id);
        return validateAndReturn(userModel, id);
    }

    private UserDetails validateAndReturn(UserModel user, Object param) {
        if (user == null) {
            throw new UsernameNotFoundException(param.toString());
        }
        else {
            return UserPrincipal.create(user);
        }
    }
}
