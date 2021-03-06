package org.sut.cashmachine.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.dao.user.UserRolesRepository;
import org.sut.cashmachine.exception.ResourceNotFoundException;
import org.sut.cashmachine.model.user.RoleModel;
import org.sut.cashmachine.model.user.UserModel;
import org.sut.cashmachine.security.CurrentUser;
import org.sut.cashmachine.security.UserPrincipal;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRolesRepository userRolesRepository;

    @RequestMapping(method = RequestMethod.POST)
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userRepository.save(userModel);
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserModel getUserByEmail(@RequestParam String email) {
        return userRepository.getUserByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    public RoleModel getRoleByUid(@RequestParam String uid) {
        return userRolesRepository.getRoleEntityByUid(uid);
    }


    @GetMapping("/me")
    @Secured({"ADMIN","CASHIER","SENIOR_CASHIER","MERCHANDISE"})
    public UserModel getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        UserModel userModel = userRepository.getUserById(userPrincipal.getId());
        if (userModel == null) {
            throw new ResourceNotFoundException("User", "id", userPrincipal.getId());
        }
        return userModel;
    }
}

